<div class="statement-body">
    <!-- BEGIN level1 level2 level3 level4 -->  
    <div style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 20px;
        margin-right: 15px;
        margin-left: 15px;
        margin-bottom: 10px;
        text-align: left;">
          <div style="text-align: center; margin-bottom: 6px">
            <img src="//cdn.codingame.com/smash-the-code/statement/league_wood_04.png" />
          </div>
          <p style="text-align: center; font-weight: 700; margin-bottom: 6px;">
            <!-- BEGIN level1 -->
            This is a <b>league based</b> challenge.
            <!-- END -->
            <!-- BEGIN level4 -->
            Welcome to the Bronze league!
            <!-- END -->
            <!-- BEGIN level2 level3 -->
            Summary of the new rules:
            <!-- END -->
          </p>
          <span class="statement-league-alert-content">
          <!-- BEGIN level1 -->
            Wood leagues should be considered as a tutorial which lets players discover the different rules of the game. <br>
            In Bronze league, all rules will be unlocked and the real challenge will begin.
          <!-- END -->
          <!-- BEGIN level2 -->
            Creatures now have special abilities.
          <!-- END -->
          <!-- BEGIN level3 -->
            There is a new type of card: items.
          <!-- END -->
          <!-- BEGIN level4 -->
            There are three additionnal abilities to take into account. Enjoy the game!
          <!-- END -->
          </span>
      </div>
      <!-- END -->
      
      <!-- GOAL -->
      <!-- LEAGUES level1 level2 level3 level4 level5 -->
      <div class="statement-section statement-goal">
        <h2>
          <span class="icon icon-goal">&nbsp;</span>
          <span>The Goal</span>
        </h2>
        <div class="statement-goal-content">
          Draft a deck of cards, battle an opponent with those cards and reduce their Health Points (HP) from 30 to 0. 
        </div>
      </div>

<!-- RULES -->
<div class="statement-section statement-rules">
  <h2>
    <span class="icon icon-rules">&nbsp;</span>
    <span>Rules</span>
  </h2>
  <div class="statement-rules-content">
    
This game is a two-player card game which is played in two phases: the <const>Draft</const> phase and the <const>Battle</const> phase.
    <ul style="padding-bottom: 0;">
      <li>
        During the
        <const>Draft</const> phase, both players must create a deck of <const>30</const> cards.
      </li>
      <li>Once the <const>Draft</const> phase is over, both decks are shuffled.</li>
      <li>
        During the
        <const>Battle</const>, the board is divided in two parts: each player plays cards from their hand on their side of the board.
      </li>
      <!-- BEGIN level2 -->
      <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
      <!-- END -->
      <!-- BEGIN level3 level4 level5 -->
      <li>
      <!-- END -->
      <!-- BEGIN level2 level3 level4 level5 -->
        Each player starts with <const>30</const> HP. Some cards can increase this number.
      </li>
      <!-- END -->
      <li>
        To reduce the health points of an opponent, the player must make use of cards to deal
        <strong>damage</strong>.
      </li>
    </ul>

<h2 style="margin-top: 30px;">
  <span class="icon icon-example">&nbsp;</span>
  <span>Draft Phase</span>
</h2>
<ul style="padding-bottom: 0;">
  <li>For <const>30</const> turns, both players are given a choice between 3 different cards. Players select the card they want to add to their
    deck by using the
    <action>PICK</action> command followed by
    <const>0</const>,
    <const>1</const> or
    <const>2</const>.
  </li>
  <li>By default, the
    <action>PASS</action> command will pick the first card.</li>
    <li>Both players can select the same card, they will each receive a copy.</li>
</ul>


<h2 style="margin-top: 30px;">
  <span class="icon icon-example">&nbsp;</span>
  <span>Battle Phase</span>
  </h2>
  <br>
<strong>Card Draw</strong>

<ul style="padding-bottom: 0;">
  <li>
First player starts with <const>4</const> cards in hand whereas the second player starts with <const>5</const>.
</li>
<li>
  Each turn, the active player draws one additional card from their deck.
</li>
<!-- BEGIN level2 -->
<li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
    
Some cards can make players draw additional cards at the beginning of the next turn when played.
    
</li>
<!-- END -->
<!-- BEGIN level3 level4 level5 -->
<li>
Some cards can make players draw additional cards at the beginning of the next turn when played.
</li>
<!-- END -->
<li>
  Both players possess <const>runes</const> which have an impact on card draw. 
  <!-- BEGIN level1 level2 level3 -->
  However, <const>runes</const> should be ignored until the Bronze league.
  <!-- END -->
  <!-- BEGIN level4 -->
  <span style="color: #7cc576;
  background-color: rgba(124, 197, 118,.1);
  padding: 2px;">
  <br> More details in Advanced Details section.
</span>
  <!-- END -->
  <!-- BEGIN level5 -->
  More details in Advanced Details section.
  <!-- END -->
</li>
</ul>

<br>
<strong>Mana</strong>
<ul style="padding-bottom: 0;">
  <li>
    Mana is necessary to play cards. 
  </li>
  <li>Both players start with
    <const>1 max mana</const>.
  </li>
  <li>
    Each turn, the active player is granted one additional max mana, unless they already have <const>12</const> max mana.
  </li>
  <li>
  Each player can spend as much mana per turn as they have max mana.
</li>
</ul>

<h2 style="margin-top: 30px;">
    <span class="icon icon-example">&nbsp;</span>
    <span>Card Types</span>
    </h2>
<!-- BEGIN level1 level2 -->
In this league, there is only one type of card: <const>Creatures</const>. 
<!-- END -->
<!-- BEGIN level3  -->
<div style="color: #7cc576;
       background-color: rgba(124, 197, 118,.1);
       padding: 2px; display:inline-block;">
There are two different types of cards: <const>Creatures</const> and <const>Items</const>.
</div>
<!-- END -->
<!-- BEGIN level4 level5 -->
There are two different types of cards: <const>Creatures</const> and <const>Items</const>.
<!-- END -->
<br/>
<br/>
<strong>Creatures</strong><br/>
  <ul style="padding-bottom: 0;">
    <li>
     Placing a creature card from the hand to the board is called <b>summoning</b>.
     A player summons <const>creatures</const> on their side of the board.
    They are used to attack the opponent and also serve as a defense against the creatures of the opposing player.
  </li>
  <li>
    Creatures have a cost in mana, attack points and defense points.
    <!-- BEGIN level2 -->
    <div style="color: #7cc576;
           background-color: rgba(124, 197, 118,.1);
                  padding: 2px; display:inline-block;">
    Some creatures start with certain abilities.
    </div>
    <!-- END -->
    <!-- BEGIN level3 level4 level5 -->
    Some creatures start with certain abilities.
    <!-- END -->
  </li>
  <li>
    By default, creatures can't attack the turn they are summoned. They can attack once per turn only.
  </li>
  <li>
    When a creature attacks another one, they both deal <strong>damage</strong> equals to their attack to the defense of the other creature. When a creature attacks the opponent, it deals <strong>damage</strong> equals to its attack to the HP of the opponent.
  </li>
  <li> 
  Creatures are removed from play when their defense reaches <const>0</const> or less.
  </li>
<!-- BEGIN level2 -->
<li style="color: #7cc576;
background-color: rgba(124, 197, 118,.1);
padding: 2px;">
  Creatures can have an effect on the player's health, the opponent's health or the card draw of the player when played.
</li>
<li style="color: #7cc576;
background-color: rgba(124, 197, 118,.1);
padding: 2px;">
    
  Creatures can have different abilities:
    <ul style="padding-top: 0; padding-bottom: 0;">
      <li>
        <const>Breakthrough</const>: Creatures with Breakthrough can deal extra <strong>damage</strong> to the opponent when they attack enemy creatures. If their attack <strong>damage</strong> is greater than the defending creature's defense, the excess <strong>damage</strong> is dealt to the opponent.
      </li>
      <li>
        <const>Charge</const>: Creatures with Charge can attack the turn they are summoned.
      </li>
      <li>
        <const>Guard</const>: Enemy creatures must attack creatures with Guard first.
      </li>
    </ul>
  
</li>

<!-- END -->
<!-- BEGIN level3 level4 level5 -->
<li>
  Creatures can have an effect on the player's health, the opponent's health or the card draw of the player when played.
</li>
<li>
  Creatures can have different abilities:
    <ul style="padding-top: 0; padding-bottom: 0;">
      <li>
        <const>Breakthrough</const>: Creatures with Breakthrough can deal extra <strong>damage</strong> to the opponent when they attack enemy creatures. If their attack <strong>damage</strong> is greater than the defending creature's defense, the excess <strong>damage</strong> is dealt to the opponent.
      </li>
      <li>
        <const>Charge</const>: Creatures with Charge can attack the turn they are summoned.
      </li>
      <!-- BEGIN level4 -->
      <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
          <const>Drain</const>: Creatures with Drain heal the player of the amount of the <strong>damage</strong> they deal (when attacking only).
      </li>
      <!-- END -->
      <!-- BEGIN level5 -->
      <li>
        <const>Drain</const>: Creatures with Drain heal the player of the amount of the <strong>damage</strong> they deal (when attacking only).
      </li>
      <!-- END -->
      <li>
        <const>Guard</const>: Enemy creatures must attack creatures with Guard first.
      </li>
      <!-- BEGIN level4 -->
      <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">  
        <const>Lethal</const>: Creatures with Lethal kill the creatures they deal <strong>damage</strong> to.
      </li>
      <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
          <const>Ward</const>: Creatures with Ward ignore once the next <strong>damage</strong> they would receive from any source. The "shield" given by the Ward ability is then lost.
      </li>
      <!-- END -->
      <!-- BEGIN level5 -->
      <li>
          <const>Lethal</const>: Creatures with Lethal kill the creatures they deal <strong>damage</strong> to.
        </li>
        <li>
          <const>Ward</const>: Creatures with Ward ignore once the next <strong>damage</strong> they would receive from any source. The "shield" given by the Ward ability is then lost.
        </li>
        <!-- END -->
    </ul>
</li>
<!-- END -->
</ul>

<!-- BEGIN level3 -->
<div style="color: #7cc576;
       background-color: rgba(124, 197, 118,.1);
       padding: 2px; display:inline-block;">
<!-- END -->
<!-- BEGIN level4 level5 -->
<div>
<!-- END -->
<!-- BEGIN level3 level4 level5 -->
<strong>Items</strong><br/>
<br/>
<ul style="padding-top: 0; padding-bottom: 0;">
<li>
    When played, <const>items</const> have an immediate and permanent effect on the board or on the players. They are then removed from play.
    </li>
    <li>
    Items have a cost in mana and one or multiple effects out of the following:
      <ul style="padding-bottom: 0;">
        <li>
          Permanent modifier of a creature's attack and/or defense characteristics. Example: +0/+2 or -1/-1.
        </li>
        <li>
          The addition or removal of one or more abilities to one creature.
        </li>
        <li>
          Additional card draw the next turn they're played.
        </li>
        <li>
          Health gain for the player or health loss for the opponent.
        </li>
      </ul>
    </li>
    <li>
      There are three types of <const>items</const>:
      <ul style="padding-bottom: 0;">
        <li>
          <const>Green items</const> should target the active player's creatures. They have a positive effect on them.
        </li>
        <li>
          <const>Red items</const> should target the opponent's creatures. They have a negative effect on them.
        </li>
        <li>
          <const>Blue items</const> can be played with the "no creature" target identifier (<const>-1</const>) to give the active player a positive effect or cause <strong>damage</strong> to the opponent, depending on the card. Blue items with negative defense points can also target enemy creatures.
        </li>
      </ul>
    </li>
</ul>
</div>
<!-- END -->
<h2 style="margin-top: 30px;">
    <span class="icon icon-example">&nbsp;</span>
    <span>Gameplay</span>
</h2>

<br/><strong>Possible Actions</strong>
<ul style="padding-bottom: 0;">
    <li><action>SUMMON id</action> to summon the creature <const>id</const> from your hand.</li>
    <li><action>ATTACK id1 id2</action> to attack creature <const>id2</const> with creature <const>id1</const>.</li>
    <li><action>ATTACK id -1</action> to attack the opponent directly with creature <const>id</const>.</li>
    <!-- BEGIN level3 -->
    <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
    <!-- END -->
    <!-- BEGIN level4 level5 -->
    <li>
    <!-- END -->
    <!-- BEGIN level3 level4 level5 -->
    <action>USE id1 id2</action> to use item <const>id1</const> on creature <const>id2</const>.
    <!-- END -->
    </li>

    <!-- BEGIN level3 -->
    <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
    <!-- END -->
    <!-- BEGIN level4 level5 -->
    <li>
    <!-- END -->
    <!-- BEGIN level3 level4 level5 -->
    <action>USE id -1</action> to use item <const>id</const>.
    <!-- END -->
    </li>
    
<li>
    <action>PASS</action> to do nothing this turn.
</li>
</ul>
A player can do any number of valid actions during one turn. Actions must be separated by a semi-colon <action>;</action>.<br/>
<br/>
  <strong>Game End</strong>
<ul style="padding-bottom: 0;">
  <li>
    The game is over once any player reaches <const>0</const> or less HP.
  </li>
</ul>

</div>
</div>
          <!-- Victory conditions -->
          <div class="statement-victory-conditions">
            <div class="icon victory"></div>
            <div class="blk">
              <div class="title">Victory Conditions</div>
              <div class="text">
                <ul style="padding-bottom: 0;">
                  <li>
                    Reduce your opponent Health Points (HP) from <const>30</const> to <const>0</const> or less.
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <!-- Lose conditions -->
          <div class="statement-lose-conditions">
            <div class="icon lose"></div>
            <div class="blk">
              <div class="title">Loss Conditions</div>
              <div class="text">
                <ul style="padding-bottom: 0;">
                  <li>
                    Your HP gets reduced to <const>0</const> or less.
                  </li>
                  <li>
                    You do not respond in time or output an unrecognized command.
                  </li>
                </ul>
              </div>
              </div>
          </div>
          <br>
      <!-- EXPERT RULES -->
      <div class="statement-section statement-expertrules">
        <h2>
          <span class="icon icon-expertrules">&nbsp;</span>
          <span>Advanced Details</span>
        </h2>
        <div class="statement-expert-rules-content">
        
        <!-- BEGIN level1 level2 level3 -->
          Advanced details will be available in the Bronze league, as well as the game's source code for reference. <br> <br>
        <!-- END -->
        <!-- BEGIN level4 -->
        <div style="color: #7cc576;
               background-color: rgba(124, 197, 118,.1);
                      padding: 2px; display:inline-block;">
        <!-- END -->

        <!-- BEGIN level4 level5 -->
        You can see the game's source code on <a href="https://github.com/CodinGame/LegendsOfCodeAndMagic">https://github.com/CodinGame/LegendsOfCodeAndMagic</a>.
        <br>
        <br>
              <strong>Runes</strong>
              <ul style="padding-bottom: 0;">
                <li>
                  Each player has <const>5</const> runes corresponding to the <const>25</const>, <const>20</const>, <const>15</const>, <const>10</const> and <const>5</const> HP thresholds.
                </li>
                <li>
                  The first time a player's HP go below one of these thresholds, that player loses a rune and will draw an additional card at the beginning of the next turn. A maximum of 5 cards can thus be drawn this way during the entire game.
                </li>
                <li>
                  When players have no more cards in their decks and must draw a card, they lose a rune and reach the corresponding HP threshold. <br>
                  Example: a player has 23 HP, 4 runes remaining and no more cards in the deck. If that player must draw a card, the player loses a rune (the 20 HP rune) and 3 HP to reach 20.
                </li>
                <li>
                  If a player has no more runes, no more cards in the deck and must draw a card, that player's HP reaches 0.
                </li>
              </ul>
              <strong>Constraints</strong>
               <ul style="padding-bottom: 0;">
                <li>
                  If a player already has the maximum number of <const>8</const> cards in hand and must draw, the draw is cancelled.
                </li>
                <li>
                  If a player already has the maximum number of <const>6</const> creatures on board and tries summoning a new one, the summoning action is cancelled.
                  </li>
                <li>
                  If a player tries to attack an untargetable target (wrong instance id or presence of other defensive creatures with Guard) with one of his creatures, the attack action is cancelled.
                </li>
                <li>
                  Once a player has played over <const>50</const> turns, their deck is considered empty.
                </li>
              </ul>
              <strong>Abilities special cases</strong>
              <ul style="padding-bottom: 0;">
                <li>
                  Giving an ability to a creature with that same ability has no effect.
                </li>
                <li>
                  Attacking a creature with Ward with a creature with Lethal does not kill the creature (since no <strong>damage</strong> is dealt to the creature).
                </li>
                <li>
                  Attacking a creature with Ward with a creature with Breakthrough never deals excess <strong>damage</strong> to the opponent (since no <strong>damage</strong> is dealt to the creature).
                </li>
                <li>
                  Attacking a creature with Ward with a creature with Drain does no heal the player (since no <strong>damage</strong> is dealt to the creature).
                </li>
              </ul>

              <!-- END -->
              <!-- BEGIN level4 -->
            </div>
            <!-- END -->
        
        </div>
      </div>
      <!-- PROTOCOL -->
      <div class="statement-section statement-protocol">
        <h2>
          <span class="icon icon-protocol">&nbsp;</span>
          <span>Game Input</span>
        </h2>
        <!-- Protocol block -->
        <div class="blk">
  <div class="title">Input for one game turn</div>
          <div class="text">
            
            <span class="statement-lineno">First 2 lines</span>: for each player, <var>playerHealth</var>, <var>playerMana</var>, <var>playerDeck</var> and <var>playerRune</var>:
            <ul style="padding-bottom: 0;">
              <li>
                Integer <var>playerHealth</var>: the remaining HP of the player.
              </li>
              <li>
                Integer <var>playerMana</var>: the current maximum mana of the player.
              </li>
              <li>
                Integer <var>playerDeck</var>: the remaining number of cards in the player's deck.
              </li>
              <li>
                Integer <var>playerRune</var>: 
                <!-- BEGIN level1 level2 level3 -->
                to be ignored in this league
                <!-- END -->
                <!-- BEGIN level4 -->
                <span style="color: #7cc576;
                  background-color: rgba(124, 197, 118,.1);
                  padding: 2px;">
                the next remaining rune of a player.
                </span>
                <!-- END -->
                <!-- BEGIN level5 -->
                the next remaining rune of a player.
                <!-- END -->
              </li>
            </ul>
            The player's input comes first, the opponent's input comes second.<br>
            <br>
            During the Draft phase, <var>playerMana</var> is always <const>0</const>.<br>
            <br>
            <span class="statement-lineno">Next line</span>: Integer <var>opponentHand</var>, the total number of cards in the opponent's hand. These cards are hidden until they're played.<br>
            <br>
            <span class="statement-lineno">Next line</span>: Integer <var>cardCount</var>: during the Battle phase, the total number of cards on the board and in the player's hand. During the Draft phase, always <const>3</const>.<br>
            <br>
            <span class="statement-lineno">Next <var>cardCount</var> lines</span>: for each card, <var>cardNumber</var>, <var>instanceId</var>, <var>location</var>, <var>cardType</var>, <var>cost</var>, <var>attack</var>, <var>defense</var>, <var>abilities</var>, <var>myhealthChange</var>, <var>opponentHealthChange</var> and <var>cardDraw</var>:
            <ul style="padding-bottom: 0;">
              <li>
                Integer <var>cardNumber</var>: the identifier of a card (see <a href="https://files.codingame.com/legends-of-code-and-magic/index_v2.html">complete list</a>).
              </li>
              <li>
                Integer <var>instanceId</var>: the identifier representing the instance of the card (there can be multiple instances of the same card in a game).
              </li>
              <li>
                Integer <var>location</var>, during the Battle phase: 
                <ul style="padding-bottom: 0; padding-top: 0; margin-bottom: 10px;">
                    <li><const>0</const>: in the player's hand</li>
                    <li><const>1</const>: on the player's side of the board</li>
                    <li><const>-1</const>: on the opponent's side of the board</li>
                </ul>
                Always <const>0</const> during the Draft phase.
                <!-- BEGIN level3 -->
                <div style="color: #7cc576;
                  background-color: rgba(124, 197, 118,.1);
                  padding: 2px; display:inline-block;">
                Always <const>0</const> for items.
                </div>
                <!-- END -->
                <!-- BEGIN level4 level 5 -->
                Always <const>0</const> for items.
                <!-- END -->
              </li>
              <!-- BEGIN level1 level2 -->
              <li>Integer <var>cardType</var>: always <const>0</const> for this league.</li>
              <!-- END -->
              <!-- BEGIN level3 -->
              <li style="color: #7cc576;
              background-color: rgba(124, 197, 118,.1);
              padding: 2px;">
              <!-- END -->
              <!-- BEGIN level4 level5 -->
              <li>
              <!-- END -->
              <!-- BEGIN level3 level4 level5 -->
                Integer <var>cardType</var>: 
                <ul style="padding-bottom: 0; padding-top:0;">
                    <li><const>0</const>: Creature</li>
                    <li><const>1</const>: Green item</li>
                    <li><const>2</const>: Red item</li>
                    <li><const>3</const>: Blue item</li>
                </ul>
              <!-- END -->
              </li>
              <li>
                Integer <var>cost</var>: the mana cost of the card,
              </li>
              <li> 
                Integer <var>attack</var>:
                <!-- BEGIN level1 level2 -->
                the attack points of the creature.
                <!-- END -->
                <!-- BEGIN level3 -->
                <ul style="padding-bottom: 2px; padding-top: 2px; color: #7cc576;
                background-color: rgba(124, 197, 118,.1);">
                  <li>Creature: its attack points</li>
                  <li>Item: its attack modifier</li>
                </ul>
                <!-- END -->
                <!-- BEGIN level4 level5 -->
                <ul style="padding-bottom: 0">
                    <li>Creature: its attack points</li>
                    <li>Item: its attack modifier</li>
                  </ul>
                  <!-- END -->
              </li>
              <li> 
                Integer <var>defense</var>:
                <!-- BEGIN level1 level2 -->
                the defense points of the creature.
                <!-- END -->
                <!-- BEGIN level3 -->
                <ul style="padding-bottom: 2px; padding-top: 2px; color: #7cc576;
                background-color: rgba(124, 197, 118,.1);">
                  <li>Creature: its defense points</li>
                  <li>Item: its defense modifier. Negative values mean this causes damage.</li>
                </ul>
                <!-- END -->
                <!-- BEGIN level4 level5 -->
                <ul style="padding-bottom: 0">
                    <li>Creature: its defense points</li>
                    <li>Item: its defense modifier. Negative values mean this causes damage.</li>
                  </ul>
                <!-- END -->
              </li>
              <li>
                String <var>abilities</var> of size <const>6</const>:
              <!-- BEGIN level1 -->
              to be ignored in this league
              <!-- END -->
              
              <!-- BEGIN level2 -->
              <span style="color: #7cc576;
                    background-color: rgba(124, 197, 118,.1);
                    padding: 2px;">
              the abilities of a card. Each letter representing an ability (<const>B</const> for Breakthrough, <const>C</const> for Charge and <const>G</const> for Guard).
              </span>
              <!-- END -->
              <!-- BEGIN level3 -->
              the abilities of a card. Each letter representing an ability (<const>B</const> for Breakthrough, <const>C</const> for Charge and <const>G</const> for Guard).
              <!-- END -->
              <!-- BEGIN level4 level5 -->
              the abilities of a card. Each letter representing an ability (<const>B</const> for Breakthrough, <const>C</const> for Charge and <const>G</const> for Guard, 
              <!-- END -->
              <!-- BEGIN level4 -->
              <span style="color: #7cc576;
              background-color: rgba(124, 197, 118,.1);
              padding: 2px;">
              <const>D</const> for Drain, <const>L</const> for Lethal and <const>W</const> for Ward).
              </span>
              <!-- END -->
              <!-- BEGIN level5 -->
              <const>D</const> for Drain, <const>L</const> for Lethal and <const>W</const> for Ward).
              <!-- END -->
              </li>
              <li>
                Integer <var>myHealthChange</var>: 
                <!-- BEGIN level1 -->
                to be ignored in this league.
                <!-- END -->
                <!-- BEGIN level2 -->
                <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                  the health change for the player.
                </span>
                <!-- END -->
                <!-- BEGIN level3 level4 level5 -->
                the health change for the player.
                <!-- END -->
              </li>
              <li>
                Integer <var>opponentHealthChange</var>: 
                <!-- BEGIN level1 -->
                to be ignored in this league.
                <!-- END -->
                <!-- BEGIN level3 level4 level5 -->
                the health change for the opponent.
                <!-- END -->
                <!-- BEGIN level2 -->
                <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                  the health change for the opponent.
                </span>
                <!-- END -->
              </li>
              <li>
                Integer <var>cardDraw</var>: 
                <!-- BEGIN level1 -->
                to be ignored in this league.
                <!-- END -->
                <!-- BEGIN level2 -->
                <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                  the additional number of cards drawn next turn for the player.
                </span>
                <!-- END -->
                <!-- BEGIN level3 level4 level5 -->
                the additional number of cards drawn next turn for the player.
                <!-- END -->
              </li>
            </ul>
          
        </div>
        </div>

        <!-- Protocol block -->
        <div class="blk">
  <div class="title">Output for one game turn of the Draft</div>
          <div class="text">
          
            <ul style="padding-bottom: 0;">
              <li><action>PICK nb</action> where <var>nb</var> equals <const>0</const>, <const>1</const> or <const>2</const> to choose one of the three proposed cards to add to your deck.
              </li>
              <li>
                <action>PASS</action> to do nothing (picks the 1st card by default).
              </li>
            </ul>

</div>
  <div class="title">Output for one game turn of the Card Battle</div>
          <div class="text">
            
            The available actions are:
            <ul style="padding-bottom: 0;">
              <li><action>SUMMON <var>id</var></action> to summon the <const>creature</const> of instanceId <var>id</var> from the player's hand.
              </li>
              <li><action>ATTACK <var>idAttacker</var> <var>idTarget</var></action> to attack an opposing creature or opposing player of instanceId <var>idTarget</var> with a creature on the board of instanceId <var>idAttacker</var>.<br />
              <var>idTarget</var> can be the "no-creature" identifier <action>-1</action>. It is used to attack the opponent directly.
              </li>
              <!-- BEGIN level3 -->
                <li style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
              <!-- END -->
              <!-- BEGIN level4 level5 -->
                <li>
              <!-- END -->
              <!-- BEGIN level3 level4 level5 -->
              <action>USE <var>idCard</var> <var>idTarget</var></action> to use an <const>item</const> of instanceId <var>idCard</var> on a creature of instanceId <var>idTarget</var> or without a target with the "no-creature" identifier <action>-1</action>.
                </li>
              <!-- END -->
              <li>
                <action>PASS</action> to do nothing. 
              </li>
            </ul>
          Players may use several actions by using a semi-colon <action>;</action>. <br>
          Players may append text to each of their actions, it will be displayed in the viewer. <br> <br>
          Example: <action>SUMMON 3;ATTACK 4 5 yolo; ATTACK 8 -1 no fear</action>.

          </div>
        </div>
          <!-- Protocol block -->
        <div class="blk">
        <div class="title">Constraints</div>
        <div class="text">
            Response time for the first draft turn ≤ <const>1000</const>ms<br>
            Response time for the first battle turn ≤ <const>1000</const>ms<br>  
            Response time per turn ≤ <const>100</const>ms<br>
            <const>0</const> ≤ <var>cost</var> ≤ <const>12</const><br>
            <const>0</const> ≤ <b>creatures on one side of the board</b> ≤ <const>6</const><br>
            <const>0</const> ≤ <b>cards in hand</b> ≤ <const>8</const><br>
        </div>
        </div>
         </div>
    <!-- BEGIN level1 level2 level3 -->
    <div style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 20px;
      margin-top: 10px;
      text-align: left;">
        <div style="text-align: center; margin-bottom: 6px">
          <img src="//cdn.codingame.com/smash-the-code/statement/league_wood_04.png" />
        </div>
        <p style="text-align: center; font-weight: 700; margin-bottom: 6px;">
            What is in store in the higher leagues?
        </p>
        <p>
        The extra rules available in higher leagues are:
        <ul style="padding-bottom: 0;" class="statement-next-rules">
        <!-- END -->
          <!-- BEGIN level1 -->
          <li>In Wood 2, creatures can have abilities.</li>
        <!-- END -->
        <!-- BEGIN level1 level2 -->
          <li>In Wood 1, players can draft and play another type of cards: <const>Items</const>.</li>
        <!-- END -->
        <!-- BEGIN level1 level2 level3 -->
        <li>In Bronze, more abilities for creatures!</li>
        <!-- END -->
        <!-- BEGIN level1 level2 level3 -->
        </ul>
      </p>
    </div>
    <!-- END -->
    
  </div>
