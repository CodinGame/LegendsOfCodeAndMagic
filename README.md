# Legends of Code and Magic (LOCM)

## Links

- [LOCM webpage](https://jakubkowalski.tech/Projects/LOCM/)
- [CodinGame challenge](https://www.codingame.com/contests/legends-of-code-and-magic-marathon)

## Justification

The reason for launching LOCM is to provide more user-friendly and simpler framework than existing Hearthstone-based frameworks (e.g. the one for the [Hearthstone AI Competition](https://dockhorn.antares.uberspace.de/wordpress/)), yet rich enough so it provides the same type of challenges as the existing CCG games like [TES:Legends](https://legends.bethesda.net/) or [Hearthstone](https://playhearthstone.com/).

Our goal is to make developing algorithms to these domains more robust, well defined, and to provide an open framework for anytime, fair comparison of AI's, so the approaches metioned e.g. [here](https://dockhorn.antares.uberspace.de/wordpress/additional-material/) can be tested and tuned more completely on the entire game rules, without restricting to only some decks or the subsets of available cards.

Moreover, our framework tackles the problem of deck choosing (as in the Arena mode in Hearthstone and TES:Legends) providing a scenario that is truly fair for both players in terms of available draft choices.

## Overview of the rules

The game takes place between two players, and is a turn-based, zero-sum game without the possibility to tie. Each player has its own deck of cards chosen from the available choices (during so-called _draft phase_) before the main game (_battle phase_).

During the game each player has some cards in _hand_ (visible to him but not to his opponent), some cards lying in front of him on the _board_ (visible to both players), and the rest of the cards remaining in the _deck_, face down in random order.

Each player has the _health_ points associated and the goal of the game is to bring down the opponent's health to zero or below. Another resource is _mana_ (some type of action points), The mana points available to a player increases every turn (up to the maximal value: 12) and they influence the cards that a player can play during his turn, as every card has a _cost_ associated.

During a turn a player can make any number of _actions_ (in any valid order) belonging to the two main types:
* he can attack using a card lying on board (target of the attack is is opponent's on-board card or the player directly)
* he can play some card from his hand, removing its cost from the mana pool available this turn. The played card can (depending on its type) be placed on board (in most cases it cannot attack the sane turn it is placed) or cause some instant effect an disappear.

At the beginning of each turn, some number of of cards from the top of player's deck (usually 1) is taken and put into the player's hand. If there are no cards in the deck, the player is receiving damage decreasing his health for every card that he tries to draw from the empty deck.

Each card, except the cost, conatins information about its _attack_, _defense_, and some additional _keywords_ (described later).

The cards that can be placed on board are called _creatures_. When they attack other creature, the attacker's attack is subtracted from the defender's defense and simultaneously the defender's attack is subtracted from the attacker's defense. Any creature with the defense zero or below is removed from the board. If the defense is positive, the creature remains, but the defense remains decreased. If the creature directly attacks the player, its attack is subtracted from the player's health.

The cards that cannot be placed onboard are called _items_. Target of an item is an onboard creature or the opponent player, and the card modifies target's attack, defense or keywords. Items always affect keywords first, and attack/defense later.

There are six keywords that influences the behavior of onboard creatures, e.g. by allowing a creature to attack the turn it is played, or protecting it from the first damage it should normally take. Also there are three additional parameters that can directly modify (increase or decrease by some number): health of the player using the card, health of his opponent, number of cards that will be drawn by the player next turn. 


Before the battle phase, described above, takes place, there is a draft phase. During the draft phase both players construct their decks.

Each step of the draft give both players the choice between three cards. Every player chooses, secretly and independently, one card to put in his deck (so two players can choose the same card). The number of choices is equal to the number of the decks should finally contain.




### Keywords (abilities)

There are 6 keywords, that can be turned on/off for every card/creature. If the card adds a keyword to a creature that already has it, there is no change. Similarly removing unpossesed keyword do not modify this keyword.

#### Breakthrough
Excess damage done on a player's turn is dealt to the opponent.

Important cases: The opponent creature has to be removed (e.g. there is never excess damage against creatures with ward).


#### Charge
When summoned, such creature can attack the turn it is played.

Important cases: If the creature gains charge keyword during the turn it can attack only if it was summoned this turn.


#### Drain
When deals damage on a player's turn, the player gain health equal to the creature's attack.

Important cases: If the defender has ward, no damage is done so no health is gained by the player.


#### Guard
Enemies must attack this creature.

#### Lethal
Removes any creature it damages.

Important cases: It does not remove creatures with ward. If lethal attack is equal zero, ability do not trigger (no damage done).


#### ~~Regenerate~~ (removed :(  )
~~Heals at the start of the player's turn.~~

~~Important cases: If at the beginning of the player's turn the creature's defense is lower then in the previous turn, the previous turn's value is restored. Otherwise, the new value of the creature's defense is stored for the next turn regeneration.~~


#### Ward
Prevent the first time the creature would take damage.

Important cases: After preventing damage, the ward disappears. Attacking with creature with 0 attack does not remove ward.


### Items

We consider three types of items that have different roles and thus their allowed targets.

#### Green items

These are items that can target only friendly (i.e. player's) onboard creatures. They usually make the target creature stronger in some way. If the item card has some keywords, this keywords are added to the creature's keywords.
 

#### Red items

These items can target only the opponent's onboard creatures. They usually make the target creature weaker. If the item card has some keywords, this keywords are removed from the creature's keywords.
 

#### Blue items

These are the special items that can be used without any creature onboard and affect only player or his opponent. The valid target for the blue item is any opponent's creature or the opponent itself. If the target is the opponent, and the card's attack is non-zero, then the attack value (usually negative) is added to the opponent's health. So that card can be used to damage creature or the opponent.


### Card Draw

#### Emergency Draw
When a player's health points reach for the first time a certain number, this player's next turn draw is increased. The milestones for emergency draws are: 25, 20, 15, 10, 5.

For example when player's health got down from 30 to 18 in one turn, at the start of his next turn he draws additional two cards. If he heals for 4 health points, and after receives 6 damage, no additional cards are drawn (the emergency draw for 20 has been already used).


#### Overdraw

If a player does not have a place in his hand for the additional cards, it only draws to fill the hand. More cards remain in the deck, and next turn draw is set to the standard value (i.e. 1). 

#### Empty deck

When player is to draw from an empty deck, it immediately loses the next rune, and his health is set to the value associated with that rune. When players has no runes remaining, his health is set to 0.

Important cases: When player has no cards in deck remaining, should draw some cards, but cannot due to the full hand, only the first rune is destroyed (regardless of the overdraw size).


### Action Commands

The player has to output a non-empty sequence of actions. The entire sequence has to be presented in one line, each acton separated by a semicolon. Empty action is a valid action, also whitespaces surrounding semicolons are permissible. Thus, e.g. a sequence with trailing or leading semicolon are acceptable. The non-empty action can be one of the following:

* `PASS` - does nothing
* `SUMMON [id] [text]` - puts a card of creature type on board
* `USE [id] [targetId] [text]` - uses a card from the hand with identifier `[id]` to use a item on the target. The target identifier `[targetId]` has to be valid identifier depending on the item's type: friendly creature, enemy creature or the opponent (which has the `-1` identifier assigned).
* `ATTACK [id] [targetId] [text]` attacks with a friendly creature with identifier `[id]` an opponent's onboard creature with identifier `[targetId]` or the player (using the identifier `-1`). Usually only one attack per creature can be performed. Attacking do not use mana points. 

 Above, `[text]` is (optional - can be empty) any sequence of characters not containing a semicolon. The content of `[text]` will be displayed as a chat message during the visualization (after each action).

Important case: Actions that are syntactically valid, but not legal (e.g. summoning creature that is not in hand or attacking opponent while there is a guard creature onboard) will result in warning message only, so they can be safely included in the action sequence. 


### Draft probabilities

Draft contains 90 cards to choose from, presented o both players in triplets. The choices are computed as follows.

First, the game uniformly randomizes 60 cards from the cards available. Then for each draft turn, three cards are randomized for this 60. If a card already occured in this triple it is redrawn until a new card is found. Thus, in every triple all three cards are different. 


### Game options

To setup the game in a repeatable way there is a possibility to set a number of parameters. Note that the randomness in the game engine occurs only before the main game phase (choice of the cards to draft and ordering of cards within players' decks).

* `seed` it controls all random numbers within the game. All other parameters override outcomes of this option for specific applications.
* `draftChoicesSeed` controls the proposition of cards during the draft phase (this is league-dependent) 
* `shufflePlayer0Seed` controls the ordering within the deck for the cards chosen by the first player during the draft phase 
* `shufflePlayer1Seed` controls the ordering within the deck for the cards chosen by the second player during the draft phase
* `predefinedDraftIds` allows to manually specify all choices available during the draft phase (overrides the effect of `draftChoicesSeed`). There have to be 30 comma-separated triplets, each containing 3 space-separated integers with card's `baseId'`s.


Example of all parameters used. Note that in this case the result will be the same regardless of the values of `seed` and `draftChoicesSeed`, as more concrete options were directly specified.   

```
seed=12700
draftChoicesSeed=-5113144502819146988
shufflePlayer0Seed=127
shufflePlayer1Seed=333
predefinedDraftIds=1 2 3 , 3 2 1 , 2 2 2 ,160 160 160, 150 151 152, 130 131 132, 7 7 7, 8 8 8, 9 9 9, 10 10 10, 11 11 11, 12 12 12, 13 13 13, 14 14 14, 15 15 15, 16 16 16, 17 17 17, 18 18 18, 19 19 19, 20 20 20, 11 11 11, 12 12 12, 13 13 13, 14 14 14, 15 15 15, 16 16 16, 17 17 17, 18 18 18, 19 19 19, 30 30 30
```
