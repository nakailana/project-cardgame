# Personal Project: Cards Against Boredom

## About 🃏
As a busy university student, I sometimes find myself wondering *“what do I do with my life now?”* as soon as the occasional day off rolls around. **Cards Against Boredom** helps with this by randomly generating activity cards (that users pre-set)!  
The inspiration for my app comes from a small pouch I found while doing a deep clean of my closet. When I was little—before TikTok, Reels, and the emergence of elementary schoolers who owned their own smart phones—I wrote the names of various activities/games on paper slips and put them in the pouch. When I was bored, I would pull out a paper slip and do that activity.  
**Cards Against Boredom** follows this simple idea in an improved digital form, where users can make their own prompt/activity cards. Cards can also be put into themed decks, so users can create decks such as “party”, “date night ideas”, or even “game mode” if they got creative. The flexibility of card and deck making makes **Cards Against Boredom** compatible for anyone who has difficulty deciding what to do, or just needs a little spontaneity in their life :)

## User Stories
*As a user I want to be able to…* 💭

- Create a new activity card
- Add my card to a deck of my choosing
- View a list of all cards in my current deck
- Delete a card from my current deck
- Switch between different card decks
- Generate a card from my current deck

- Have the option to save my card decks and their respective cards to file when I select the quit option from the application menu
- Have the option to load my card decks and their respective cards from file when I start the application

## Instructions for End User 📃

- You can view the card display panel,  containing any existing cards, by double-clicking its corresponding deck in the main menu

- You can create a new activity card by first selecting the deck you want the card to be located in, and then pressing the 'add new card' button in the menu at the bottom of the screen

- You can delete a card by right-clicking it in the card display panel

- You can locate the visual component of the application by generating a random card

- You can save the state of the application by clicking the 'save data' button in the main menu

- You can load any saved pre-existing state of the application by clicking the 'load data' button in the main menu


## Phase 4: Task 2  ✎
A representative sample of the events that occur when the program is run:

Sun Mar 22 15:59:40 PDT 2026  
Added deck to game.


Sun Mar 22 15:59:50 PDT 2026  
Added deck to game.


Sun Mar 22 16:00:01 PDT 2026  
Added a card to deck.


Sun Mar 22 16:00:08 PDT 2026  
Added a card to deck.


Sun Mar 22 16:00:15 PDT 2026  
Added a card to deck.


Sun Mar 22 16:00:18 PDT 2026  
Drew a random card.


Sun Mar 22 16:00:24 PDT 2026  
Drew a random card with filtering


Sun Mar 22 16:00:28 PDT 2026  
Removed card from deck.

## Phase 4: Task 3 🔎
*An analysis of how to improve the program's design*

If I had more time to work on this project, I would refactor my program to reduce coupling between my model and UI package classes. Currently, I have classes to support both a CLI and GUI, so I would probably remove the UI classes for the CLI. There are also a few unneccessary dependencies between my model and GUI classes. This is especially noticable in my diagram with the Deck, DrawingSurface, and CardPanel classes. For example, there are currently fields representing the 'current Deck' across both DrawingSurface and CardPanel classes. This works for now, but in the future it could cause trouble if the current Deck field is set or changed in one class while being forgotten in the other. While it is important for the CardPanel to know what the current Deck is in order to add/remove/draw cards from that deck in response to user interaction, it is not really important for the DrawingSurface to have that information. In the future, I could refactor the DrawingSurface and CardPanel classes so that only CardPanel has a field for the current Deck.