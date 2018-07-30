<div class="statement-body">
     <!-- LEAGUE ALERT -->
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
            Ce puzzle se déroule en ligues.
            <!-- END -->
            <!-- BEGIN level4 -->
            Bienvenue en ligue Bronze !
            <!-- END -->
            <!-- BEGIN level2 level3 -->
            Résumé des nouvelles règles :
            <!-- END -->
          </p>
          <span class="statement-league-alert-content">
          <!-- BEGIN level1 -->
            Les ligues Bois doivent être considérées comme un tutoriel pour apprendre les différentes règles du jeu. <br>
            En ligue Bronze, toutes les règles sont débloquées et alors débute le challenge, le vrai.
          <!-- END -->
          <!-- BEGIN level2 -->
            Certaines créatures ont maintenant des capacités spéciales.
          <!-- END -->
          <!-- BEGIN level3 -->
            Il y a un nouveau type de carte : les Objets.
          <!-- END -->
          <!-- BEGIN level4 -->
            Il y a trois nouvelles capacités à prendre en compte. Amusez-vous bien !
          <!-- END -->
          </span>
      </div>
      <!-- END -->
      <!-- END LEAGUE ALERT -->
      <!-- GOAL -->
      <!-- LEAGUES level1 level2 level3 level4 level5 -->
      <div class="statement-section statement-goal">
        <h2>
          <span class="icon icon-goal">&nbsp;</span>
          <span>Objectif</span>
        </h2>
        <div class="statement-goal-content">
          Constituer un paquet de cartes, combattre un adversaire avec ces cartes, et réduire ses points de vie (PV) de 30 à 0. 
        </div>
      </div>
<!-- RULES -->
<div class="statement-section statement-rules">
  <h2>
    <span class="icon icon-rules">&nbsp;</span>
    <span>Règles du jeu</span>
  </h2>
  <div class="statement-rules-content">
    Ce jeu de cartes à deux joueurs se joue en deux phases: une phase de <const>"Draft"</const> et une phase de <const>Bataille</const>.
    <ul style="padding-bottom: 0;">
      <li>
        Pendant la phase de <const>Draft</const>, chaque joueur doit constituer un "deck" (paquet) de <const>30</const> cartes.
      </li>
      <li>
        Une fois que le <const>Draft</const> est terminé, les decks des deux joueurs sont mélangés.
      </li>
      <li>
        Pendant la <const>Bataille</const>, le plateau de jeu est divisé en deux : un côté pour chaque joueur.
      </li>
      <!-- BEGIN level2 -->
      <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
        Chaque joueur commence avec un total de <const>30</const> PVs. Certaines cartes peuvent amener ce total à être dépassé.
      </li>
      <!-- END -->
      <!-- BEGIN level3 level4 level5 -->
      <li>
        Chaque joueur commence avec un total de <const>30</const> PVs. Certaines cartes peuvent amener ce total à être dépassé.
      </li>
      <!-- END -->
      <li>
        Pour réduire les PVs de son adversaire, le joueur doit utiliser ses cartes pour lui infliger des <strong>dégâts</strong>.
      </li>
  </ul>

<h2 style="margin-top: 30px;">
  <span class="icon icon-example">&nbsp;</span>
  <span>Le Draft</span>
</h2>
<ul style="padding-bottom: 0;">
  <li>&Agrave; chaque tour (pendant 30 tours), un choix de 3 cartes est proposé aux deux joueurs. Les joueurs sélectionnent la carte qu'ils veulent ajouter à leur deck en utilisant la commande <action>PICK</action> suivi de <const>0</const>, <const>1</const> ou <const>2</const>.
  </li>
  <li>Par défaut, la commande <action>PASS</action> sélectionnera la première carte.
  </li>
    <li>
      Les deux joueurs peuvent choisir la même carte, ils recevront chacun une copie.
    </li>
</ul>

  <h2 style="margin-top: 30px;">
    <span class="icon icon-example">&nbsp;</span>
    <span>La Bataille</span>
    </h2>
    <p>
    <strong>Pioche des cartes</strong>

    <ul style="padding-bottom: 0;">
      <li>
        Le premier joueur commence avec <const>4</const> cartes en main, alors que son adversaire commence avec <const>5</const>.
      </li>
      <li>
        &Agrave; chaque tour, le joueur actif pioche la première carte de son deck.
      </li>
      <!-- BEGIN level2 -->
      <li style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 2px;">
        Certaines cartes, lorsqu'elles sont jouées peuvent faire piocher des cartes additionnelles au joueur, au début du tour suivant.
      </li>
      <!-- END -->
      <!-- BEGIN level3 level4 level5 -->
      <li>
        Certaines cartes, lorsqu'elles sont jouées peuvent faire piocher des cartes additionnelles au joueur, au début du tour suivant.
      </li>
      <!-- END -->
      <li>
        Chaque joueur possède des <const>runes</const> qui ont un impact sur la pioche de cartes.
        <!-- BEGIN level1 level2 level3 -->
        <br> Néanmoins, les <const>runes</const> doivent être ignorées dans les ligues Bois. Plus de détails en ligue Bronze.
        <!-- END -->
        <!-- BEGIN level4 -->
        <span style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
          Plus de détails dans la section "Détails avancés".
      </span>
        <!-- END -->
        <!-- BEGIN level5 -->
        Plus de détails dans la section "Détails avancés".
        <!-- END -->
      </li>
    </ul>
  </p>
  <p>
  <strong>La mana</strong>
  <ul style="padding-bottom: 0;">
    <li>
      La mana est nécessaire pour jouer des cartes.
    </li>
    <li>
      Chaque joueur commence avec <const>1 mana maximum</const>.
    </li>
    <li>
      A chaque tour, le joueur actif obtient un mana maximum additionel, à moins d'en avoir déjà <const>12</const>.
    </li>
    <li>
      Chaque joueur peut dépenser autant de mana par tour que de mana maximum.
    </li>
  </ul>
  </p>
  <p>
  <p>
  <h2 style="margin-top: 30px;">
    <span class="icon icon-example">&nbsp;</span>
    <span>Les types de cartes</span>
  </h2>
  <!-- BEGIN level1 level2 -->
  Dans cette ligue, il n'y a qu'un type de carte : les <const>Créatures</const>.
  <!-- END -->
  <!-- BEGIN level3 -->
  <div style="color: #7cc576;
  background-color: rgba(124, 197, 118,.1);
  padding: 2px; display:inline-block;">
    Il existe deux types de cartes différentes : les <const>Créatures</const> et les <const>Objets</const>.
  </div>
  <!-- END -->
  <!-- BEGIN level4 level5 -->
  Il existe deux types de cartes différentes : les <const>Créatures</const> et les <const>Objets</const>.
  <!-- END -->
  <br/><br/>
  <strong>Créatures</strong><br/>
  <ul style="padding-bottom: 0;">
    <li>
      "Invoquer une créature" signifie jouer une carte de créature sur son côté du plateau de jeu depuis sa main.
      Chaque joueur invoque des créatures qui ensuite pourront attaquer l'adversaire. Elles sont aussi un moyen de défense contre les créatures adverses.
    </li>
    <li>
      Une créature est définie par son coût en mana, ainsi que par ses caractéristiques d'attaque et de défense.
    </li>
    <li>
      Par défaut, une créature ne peut pas attaquer le tour où elle est invoquée. <br>
      Une créature ne peut attaquer qu'une seule fois par tour.
    </li>
    <li>
      Lorsqu'une créature en attaque une autre, chacune inflige autant de <strong>dégâts</strong> que son attaque à la défense de l'autre créature. Si une créature attaque un joueur, elle lui inflige directement ses dégâts d'attaque.
    </li>
    <li>
      Si les PVs d'une créature tombent à <const>0</const> ou moins, alors elle est retirée du jeu.
    </li>
  <!-- BEGIN level2 -->
    <li style="color: #7cc576;
    background-color: rgba(124, 197, 118,.1);
    padding: 2px;">
      Au moment où elle est invoquée, une créature peut avoir un effet sur les PVs du joueur qui l'invoque, les PVs de son adversaire ou sur la pioche de carte.
    </li>
    <li style="color: #7cc576;
    background-color: rgba(124, 197, 118,.1);
    padding: 2px;">
      Une créature peut aussi avoir une ou plusieurs capacités parmi les suivantes:
      <ul style="padding-top: 0; padding-bottom: 0;">
        <li>
          <const>Breakthrough</const> (Percée) : une créature avec Breakthrough peut infliger des <strong>dégâts</strong> supplémentaires à l'adversaire quand elle attaque une autre créature. Si ses <strong>dégâts</strong> d'attaque sont supérieurs à la défense de la créature défenseuse, alors l'excès de <strong>dégâts</strong> est infligé au joueur défenseur.
        </li>
        <li>
          <const>Charge</const> : une créature avec Charge peut attaquer le tour où elle est invoquée.
        </li>
        <li>
          <const>Guard</const> (Garde) : une créature attaquante doit attaquer une creature avec Guard du joueur défenseur en priorité.
        </li>
      </ul>
    </li>
  <!-- END -->
  <!-- BEGIN level3 level4 level5 -->
  <li>
      Au moment où elle est invoquée, une créature peut avoir un effet sur les PVs du joueur qui l'invoque, les PVs de son adversaire ou sur la pioche de carte.
    </li>
    <li>
    Une créature peut aussi avoir une ou plusieurs capacités parmi les suivantes:
      <ul style="padding-top: 0; padding-bottom: 0;">
        <li>
          <const>Breakthrough</const> (Percée) : une créature avec Breakthrough peut infliger des <strong>dégâts</strong> supplémentaires à l'adversaire quand elle attaque une autre créature. Si ses <strong>dégâts</strong> d'attaque sont supérieurs à la défense de la créature défenseuse, alors l'excès de <strong>dégâts</strong> est infligé au joueur défenseur.
        <li>
          <const>Charge</const> : une créature avec Charge peut attaquer le tour où elle est invoquée.
        </li>
        <!-- BEGIN level4 -->
        <li style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
          <const>Drain</const> (Vol de vie) : une créature avec Drain ajoute autant de PVs que de <strong>dégâts</strong> qu'elle inflige (quand elle attaque seulement).
        </li>
        <!-- END -->
        <!-- BEGIN level5 -->
        <li>
          <const>Drain</const> (Vol de vie) : une créature avec Drain ajoute autant de PVs que de <strong>dégâts</strong> qu'elle inflige (quand elle attaque seulement).
        </li>
          <!-- END -->
        <li>
          <const>Guard</const> (Garde) : une créature attaquante doit attaquer une creature avec Guard du joueur défenseur en priorité.
        </li>
        <!-- BEGIN level4 -->
        <li style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
          <const>Lethal</const> (Létalité) : une créature avec Lethal tue toutes les créatures auxquelles elle inflige des <strong>dégâts</strong>.
        </li>
        <li style="color: #7cc576;
        background-color: rgba(124, 197, 118,.1);
        padding: 2px;">
          <const>Ward</const> (Bouclier) : une créature avec Ward ignore les prochains <strong>dégâts</strong> qu'elle se verrait recevoir (une seule fois). Le "bouclier" procuré par la capacité Ward est ensuite perdu.
        </li>
        <!-- END -->
        <!-- BEGIN level5 -->
        <li>
            <const>Lethal</const> (Létalité) : une créature avec Lethal tue toutes les créatures auxquelles elle inflige des <strong>dégâts</strong>.
          </li>
          <li>
            <const>Ward</const> (Bouclier) : une créature avec Ward ignore les prochains <strong>dégâts</strong> qu'elle se verrait recevoir (une seule fois). Le "bouclier" procuré par la capacité Ward est ensuite perdu.
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
  <strong>Objets</strong><br/>
  <ul>
  <li>
    Une fois joués, les <const>objets</const> ont un effet immédiat et permanent sur le plateau de jeu ou sur les joueurs. Ils sont ensuite retirés du jeu.
  </li>
  <li>
    Les objets sont définis par leur coût en mana ainsi que par un ou plusieurs effets parmi les suivants :
    <ul style="padding-bottom: 0;">
      <li>
        La modification des points d'attaque et/ou de défense d'une créature. Exemple: +0/+2 or -1/-1.
      </li>
      <li>
        L'ajout ou le retrait d'une ou plusieurs capacités à une créature.
      </li>
      <li>
        L'ajout d'une ou plusieurs cartes à piocher au tour suivant.
      </li>
      <li>
        Le gain et/ou perte de PVs pour le joueur et/ou son adversaire.
      </li>
    </ul>
  </li>
  <li>
    Il y a trois types d'objets:
    <ul style="padding-bottom: 0;">
      <li>
        Un <const>objet vert</const> doit cibler une créature du joueur actif. Il lui confère un effet positif.
      </li>
      <li>
        Un <const>objet rouge</const> doit cibler une créature de l'adversaire. Il lui confère un effet négatif.
      </li>
      <li>
        Un <const>objet bleu</const> doit être joué soit sur une créature de l'adversaire soit sans cible (identifiant <const>-1</const>). Dans le premier cas, l'effet est le même que pour un objet rouge. Dans le deuxième cas, la cible de l'effet de la carte, si applicable, est l'adversaire.
      </li>
    </ul>
  </li>
  <!-- END -->
  <!-- BEGIN level4 level5 -->
  <strong>Objets</strong><br/>
  <ul>
  <li>
      Une fois joués, les <const>objets</const> ont un effet immédiat et permanent sur le plateau de jeu ou sur les joueurs. Ils sont ensuite retirés du jeu.
    </li>
    <li>
      Les objets sont définis par leur coût en mana ainsi que par un ou plusieurs effets parmi les suivants :
      <ul style="padding-bottom: 0;">
        <li>
          La modification des points d'attaque et/ou de défense d'une créature. Exemple: +0/+2 or -1/-1.
        </li>
        <li>
          L'ajout ou le retrait d'une ou plusieurs capacités à une créature.
        </li>
        <li>
          L'ajout d'une ou plusieurs cartes à piocher au tour suivant.
        </li>
        <li>
          Le gain et/ou perte de PVs pour le joueur et/ou son adversaire.
        </li>
      </ul>
    </li>
    <li>
      Il y a trois types d'objets:
      <ul style="padding-bottom: 0;">
        <li>
          Un <const>objet vert</const> doit cibler une créature du joueur actif. Il lui confère un effet positif.
        </li>
        <li>
          Un <const>objet rouge</const> doit cibler une créature de l'adversaire. Il lui confère un effet négatif.
        </li>
        <li>
          Un <const>objet bleu</const> doit être joué soit sur une créature de l'adversaire soit sans cible (identifiant <const>-1</const>). Dans le premier cas, l'effet est le même que pour un objet rouge. Dans le deuxième cas, la cible de l'effet de la carte, si applicable, est l'adversaire.
        </li>
      </ul>
    </li>
    <!-- END -->
</ul>
<!-- BEGIN level3 -->
</div>
<!-- END -->
</p>
<h2 style="margin-top: 30px;">
  <span class="icon icon-example">&nbsp;</span>
  <span>Gameplay</span>
</h2>
<strong>Actions possibles</strong>
<ul style="padding-bottom: 0;">
  <li>
     <action>SUMMON id</action> pour invoquer une créature
     
  </li>
  <li>
     <action>ATTACK id1 id2</action> pour attaquer la créature <const>id2</const> avec la créature <const>id1</const>.
  </li>
  <li>
     <action>ATTACK id -1</action> pour attaquer l'adversaire avec la créature <const>id</const>.
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
     <action>USE id1 id2</action> pour utiliser l'objet <const>id1</const> sur la créature <const>id2</const>.
  </li>
  <li>
     <action>USE id -1</action> pour utiliser l'objet <const>id</const>.
     <!-- END -->
  </li>
  <li>
    <action>PASS</action> pour passer son tour.
  </li>
</ul>
Un joueur peut faire autant d'actions valides qu'il le désire pendant un tour. Les commandes doivent êtres séparées entre elles par un point-virgule (<action>;</action>).<br/>
<br/>
  <strong>Fin d'une partie</strong>
<ul style="padding-bottom: 0;">
  <li>
    La partie se termine une fois que les PVs d'un des joueurs atteint <const>0</const> ou moins.
  </li>
</ul>

</div>
</div>
          <!-- Victory conditions -->
          <div class="statement-victory-conditions">
            <div class="icon victory"></div>
            <div class="blk">
              <div class="title">Conditions de victoire</div>
              <div class="text">
                <ul style="padding-bottom: 0;">
                  <li>
                    Réduire les points de vie de votre adversaire (PVs) de <const>30</const> à <const>0</const> ou moins.
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <!-- Lose conditions -->
          <div class="statement-lose-conditions">
            <div class="icon lose"></div>
            <div class="blk">
              <div class="title">Conditions de défaite</div>
              <div class="text">
                <ul style="padding-bottom: 0;">
                  <li>
                    Votre total de PVs est réduit à <const>0</const> ou moins.
                  </li>
                  <li>
                    Votre code ne répond pas dans les temps ou retourne une commande non reconnue.
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
          <span>Détails avancés</span>
        </h2>
        <div class="statement-expert-rules-content">
        <p>
        <!-- BEGIN level1 level2 level3 -->
        Cette section sera disponible en ligue Bronze, de même que le code source du jeu. <br> <br>
        <!-- END -->
              <!-- BEGIN level4 -->
              <div style="color: #7cc576;
              background-color: rgba(124, 197, 118,.1);
              padding: 2px; display:inline-block;">
              <!-- END -->
              <!-- BEGIN level4 level5 -->
              <strong>Les runes</strong>
              <ul style="padding-bottom: 0;">
                <li>
                  Chaque joueur possède <const>5</const> runes correspondant aux paliers de <const>25</const>, <const>20</const>, <const>15</const>, <const>10</const> et <const>5</const> PVs.
                </li>
                <li>
                  La première fois que le total de PVs d'un joueur venait à passer sous l'un de ses paliers, ce joueur perdrait une rune et piocherait une carte supplémentaire au début de son prochain tour. Un maximum de <const>5</const> cartes peut donc être pioché de cette façon pendant une partie.
                </li>
                <li>
                  Quand un joueur n'a plus de carte dans son deck et doit piocher une carte, ce joueur perd une rune et son total de PVs tombe au palier correspondant. <br>
                  Exemple : un joueur a 23 PVs, 4 runes restantes et plus de cartes dans son deck. Si ce joueur doit piocher une carte, il perdra une rune (la rune de 20 PVs) et 3 PVs jusqu'à 20.
                </li>
                <li>
                  Si un joueur n'a plus de runes, ni de cartes dans son deck et doit piocher une carte, alors ses PVs tombent à 0.
                </li>
              </ul>
              <strong>Contraintes</strong>
              <ul style="padding-bottom: 0;">
               <li>
                 Si un joueur avec le nombre maximum (<const>8</const>) de cartes en main doit piocher, il ne se passe rien et la pioche de carte est annulée.
               </li>
               <li>
                 Si un joueur avec le nombre maximum (<const>6</const>) de créatures en jeu invoque une créature, l'action d'invocation est annulée.
               </li>
               <li>
                 Si un joueur désire attaquer une cible non ciblable (mauvais identifiant ou présence de créatures défensives avec Guard) avec l'une de ses créatures, l'action d'attaque est annulée.
               </li>
               <li>
                  Dès qu'un joueur dépasse son <const>50ème</const> tour, son deck est considéré comme étant vide.
                </li>
             </ul>
              <strong>Cas spéciaux de capacités</strong>
              <ul style="padding-bottom: 0;">
                <li>
                  Donner une capacité à une créature avec la même capacité n'a aucun effet à moins que ce soit une créature avec <const>Ward</const> qui a déjà perdu le "bouclier" procuré par la capacité.
                </li>
                <li>
                  La capacité <const>Lethal</const> n'a pas d'effet sur les créatures avec <const>Ward</const> qui ont encore le "bouclier" procuré par la capacité (puisqu'aucun <strong>dégât</strong> n'est infligé).
                </li>
                <li>
                  De la même façon, une créature avec <const>Breakthrough</const> qui attaque une créature avec <const>Ward</const> n'infligera pas de <strong>dégât</strong> supplémentaire à l'adversaire (puisque les <strong>dégâts</strong> sont ignorés).
                </li>
                <li>
                  De la même façon, une créature avec <const>Drain</const> qui attaque une créature avec <const>Ward</const> n'ajoutera pas de PVs au joueur attaquant (puisqu'aucun <strong>dégât</strong> n'est infligé).
                </li>
              </ul>
              <!-- END -->
              <!-- BEGIN level4 -->
            </div>
        <!-- END -->
        </p>
        </div>
      </div>
      <!-- PROTOCOL -->
      <div class="statement-section statement-protocol">
        <h2>
          <span class="icon icon-protocol">&nbsp;</span>
          <span>Protocole du jeu</span>
        </h2>
        <!-- Protocol block -->
        <div class="blk">
  <div class="title">Entrée pour un tour de jeu</div>
          <div class="text">
            <p>
            <span class="statement-lineno">2 premières lignes</span> : pour chaque joueur, <var>playerHealth</var>, <var>playerMana</var>, <var>playerDeck</var> et <var>playerRune</var>:
            <ul style="padding-bottom: 0;">
              <li>
                Un entier <var>playerHealth</var> : le total de PVs restants du joueur.
              </li>
              <li>
                Un entier <var>playerMana</var> : le mana maximum du joueur.
              </li>
              <li>
                Un entier <var>playerDeck</var> : le nombre de cartes restantes dans le deck du joueur.
              </li>
              <li>
                Un entier <var>playerRune</var> : 
                <!-- BEGIN level1 level2 level3 -->
                à ignorer dans cette ligue
                <!-- END -->
                <!-- BEGIN level4 -->
                <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                  la prochaine rune restante d'un joueur.
                </span>
                <!-- END -->
                <!-- BEGIN level5 -->
                la prochaine rune restante d'un joueur.
                <!-- END -->
              </li>
            </ul>
            L'entrée du joueur actif arrive en premier suivi de l'entrée correspondant à son adversaire.<br>
            <br>
            Pendant la phase de Draft, <var>playerMana</var> vaut toujours <const>0</const>.<br>
            <br>
            <span class="statement-lineno">Ligne suivante </span>: un entier <var>opponentHand</var>, le nombre total de cartes dans la main de l'adversaire. Ces cartes sont cachées jusqu'à qu'elles soient jouées.<br>
            <br>
            <span class="statement-lineno">Ligne suivante </span>: un entier <var>cardCount</var>, le nombre total de cartes sur le plateau de jeu et dans la main du joueur actif.<br>
            <br>
            <span class="statement-lineno"><var>cardCount</var> lignes suivantes </span>: pour chaque carte, <var>cardNumber</var>, <var>instanceId</var>, <var>location</var>, <var>cardType</var>, <var>cost</var>, <var>attack</var>, <var>defense</var>, <var>abilities</var>, <var>myhealthChange</var>, <var>opponentHealthChange</var> et <var>cardDraw</var>:
            <ul style="padding-bottom: 0;">
              <li>
                Un entier <var>cardNumber</var> : l'identifiant de la carte (voir <a href="https://files.codingame.com/legends-of-code-and-magic/index_v2.html">la liste complète</a>).
              </li>
              <li>
                Un entier <var>instanceId</var> : l'identifiant représentant l'instance de la carte (il peut y avoir plusieurs instances de la même carte dans une même partie).
              </li>
              <li>
                Un entier <var>location</var>: 
                <ul style="padding-bottom: 0; padding-top: 0;">
                    <li><const>0</const> : dans la main du joueur actif</li>
                    <li><const>1</const> : sur le plateau de jeu, du côté du joueur actif</li>
                    <li><const>-1</const> : sur le plateau de jeu, du côté de son adversaire</li>
                </ul>
                <!-- BEGIN level3 -->
                <div style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px; display:inline-block;">
                  Toujours <const>0</const> pour un objet.
                </div>
                <!-- END -->
                <!-- BEGIN level4 level5 -->
                Toujours <const>0</const> pour un objet.
                <!-- END -->
              </li>
              <li>Un entier <var>cardType</var>: 
              <!-- BEGIN level1 level2 -->
              toujours <const>0</const> dans cette ligue.</li>
              <!-- END -->
              <!-- BEGIN level3 -->
              <ul style="padding-bottom: 2px; padding-top: 2px; color: #7cc576;
                background-color: rgba(124, 197, 118,.1);">
                    <li><const>0</const> : créature</li>
                    <li><const>1</const> : objet vert</li>
                    <li><const>2</const> : objet rouge</li>
                    <li><const>3</const> : objet bleu</li>
                </ul>
              </li>
              <!-- END -->
              <!-- BEGIN level4 level5 -->
                <ul style="padding-bottom: 0; padding-top:0;">
                    <li><const>0</const> : créature</li>
                    <li><const>1</const> : objet vert</li>
                    <li><const>2</const> : objet rouge</li>
                    <li><const>3</const> : objet bleu</li>
                </ul>
              </li>
              <!-- END -->
              <li>
                Un entier <var>cost</var> : le coût en mana d'une carte,
              </li>
              <li> 
                Un entier <var>attack</var> :
                <!-- BEGIN level1 level2 -->
                les caractéristiques d'attaque d'une créature.
                <!-- END -->
                <!-- BEGIN level3 -->
                <ul style="padding-bottom: 2px; padding-top: 2px; color: #7cc576;
                background-color: rgba(124, 197, 118,.1);">
                  <li>Créature : ses caractéristiques d'attaque</li>
                  <li>Objet : son modificateur d'attaque</li>
                </ul>
                <!-- END -->
                <!-- BEGIN level4 level5 -->
                <ul style="padding-bottom: 0">
                  <li>Créature : ses caractéristiques d'attaque</li>
                  <li>Objet : son modificateur d'attaque</li>
                </ul>
                  <!-- END -->
              </li>
              <li> 
                Un entier <var>defense</var> :
                <!-- BEGIN level1 level2 -->
                les caractéristiques de défense d'une créature.
                <!-- END -->
                <!-- BEGIN level3 -->
                <ul style="padding-bottom: 2px; padding-top: 2px; color: #7cc576;
                background-color: rgba(124, 197, 118,.1);">
                  <li>Créature : ses caractéristiques de défense</li>
                  <li>Objet : son modificateur de défense</li>
                </ul>
                <!-- END -->
                <!-- BEGIN level4 level5 -->
                <ul style="padding-bottom: 0">
                  <li>Créature : ses caractéristiques de défense</li>
                  <li>Objet : son modificateur de défense</li>
                </ul>
                <!-- END -->
              </li>
              <li>
                Un String <var>abilities</var> de taille <const>6</const>:
              <!-- BEGIN level1 -->
              à ignorer dans cette ligue.
              <!-- END -->
              <!-- BEGIN level2 -->
              <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                les capacités d'une carte. Chaque lettre représente une capacité (<const>B</const> pour Breakthrough, <const>C</const> pour Charge et <const>G</const> pour Guard).
              </span>
              <!-- END -->
              <!-- BEGIN level3 -->
              les capacités d'une carte. Chaque lettre représente une capacité (<const>B</const> pour Breakthrough, <const>C</const> pour Charge et <const>G</const> pour Guard).
              <!-- END -->
              <!-- BEGIN level3 level4 level5 -->
              les capacités d'une carte. Chaque lettre représente une capacité (<const>B</const> pour Breakthrough, <const>C</const> pour Charge et <const>G</const> pour Guard, 
              <!-- END -->
              <!-- BEGIN level4 -->
              <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                <const>D</const> pour Drain, <const>L</const> pour Lethal et <const>W</const> pour Ward).
              </span>
              <!-- END -->
              <!-- BEGIN level5 -->
              <const>D</const> pour Drain, <const>L</const> pour Lethal et <const>W</const> pour Ward).
              <!-- END -->
              </li>
              <li>
                Un entier <var>myHealthChange</var> : 
                <!-- BEGIN level1 -->
                à ignorer dans cette ligue.
                <!-- END -->
                <!-- BEGIN level2 -->
                <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                  le gain de PVs pour le joueur actif.
                </span>
                <!-- END -->
                <!-- BEGIN level3 level4 level5 -->
                le gain de PVs pour le joueur actif.
                <!-- END -->
              </li>
              <li>
                Un entier <var>opponentHealthChange</var> : 
                <!-- BEGIN level1 -->
                à ignorer dans cette ligue.
                <!-- END -->
                <!-- BEGIN level2 -->
                <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                  la perte de PVs pour l'adversaire.
                </span>
                <!-- END -->
                <!-- BEGIN level3 level4 level5 -->
                la perte de PVs pour l'adversaire.
                <!-- END -->
              </li>
              <li>
                Un entier <var>cardDraw</var> : 
                <!-- BEGIN level1 -->
                à ignorer dans cette ligue.
                <!-- END -->
                <!-- BEGIN level2 -->
                <span style="color: #7cc576;
                background-color: rgba(124, 197, 118,.1);
                padding: 2px;">
                  le nombre de cartes supplémentaires à piocher au tour suivant.
                </span>
                <!-- END -->
                <!-- BEGIN level3 level4 level5 -->
                le nombre de cartes supplémentaires à piocher au tour suivant.
                <!-- END -->
              </li>
            </ul>
          </p>
        </div>
        </div>

        <!-- Protocol block -->
        <div class="blk">
  <div class="title">Sortie pour un tour de jeu du Draft</div>
          <div class="text">
            <p>
            <ul style="padding-bottom: 0;">
              <li><action>PICK nb</action> où <var>nb</var> vaut <const>0</const>, <const>1</const> ou <const>2</const> pour choisir l'une des trois cartes proposées à ajouter dans son deck.
              </li>
              <li>
                <action>PASS</action> pour ne rien faire (sélectionne la première carte par défaut).
            </ul>
          </p>
</div>
  <div class="title">Sortie pour un tour de jeu de la Bataille</div>
          <div class="text">
            <p>
            Les actions disponibles sont les suivantes :
            <ul style="padding-bottom: 0;">
              <li><action>SUMMON <var>id</var></action> pour invoquer une <const>créature</const> ayant pour identifiant (d'instance) <var>id</var> depuis la main du joueur.
              </li>
              <li><action>ATTACK <var>idAttacker</var> <var>idTarget</var></action> pour attaquer une créature adverse d'identifiant (d'instance) <var>idTarget</var> ou l'adversaire (identifiant <action>-1</action>) avec une créature d'identifiant (d'instance) <var>idAttacker</var>.
              </li>
              <!-- BEGIN level3 -->
              <li style="color: #7cc576;
              background-color: rgba(124, 197, 118,.1);
              padding: 2px;">
                <action>USE <var>idCard</var> <var>idTarget</var></action> ou <action>USE idCard</action> pour utiliser un <const>objet</const> d'identifiant (d'instance) <var>idCard</var> sur la créature (ou le joueur) d'identifant (d'instance) <var>idTarget</var> (la cible est optionelle).
              </li>
              <!-- END -->
              <!-- BEGIN level4 level5 -->
              <li>
                <action>USE <var>idCard</var> <var>idTarget</var></action> ou <action>USE idCard</action> pour utiliser un <const>objet</const> d'identifiant (d'instance) <var>idCard</var> sur la créature (ou le joueur) d'identifant (d'instance) <var>idTarget</var> (la cible est optionelle).
              </li>
              <!-- END -->
              <li>
                <action>PASS</action> pour ne rien faire et passer son tour. 
              </li>
            </ul>
          Chaque joueur peut utiliser plusieurs de ces commandes en les séparant par un point-virgule <const>;</const>. <br>
          Chaque joueur peut ajouter du texte à la suite de ses commandes. Les messages seront affichés par le lecteur de parties.<br> <br>
          Exemple : <action>SUMMON 3;ATTACK 4 5 yolo; ATTACK 8 -1 no fear</action>.
        </p>
          </div>
        </div>
          <!-- Protocol block -->
        <div class="blk">
        <div class="title">Contraintes</div>
        <div class="text">
          Temps de réponse pour le premier tour du Draft ≤ <const>1000</const>ms<br>
          Temps de réponse pour le premier tour de la Bataille ≤ <const>1000</const>ms<br>
          Temps de réponse pour un tour ≤ <const>100</const>ms<br>
            
            <const>0</const> ≤ <var>cost</var> ≤ <const>12</const><br>
            <const>0</const> ≤ <b>créatures sur un côté du plateau de jeu</b> ≤ <const>6</const><br>
            <const>0</const> ≤ <b>cartes en main</b> ≤ <const>8</const><br>
        </div>
        </div>
        </div>
    <!-- BEGIN level1 level2 level3 -->
    <!-- LEAGUE ALERT -->
    <div style="color: #7cc576;
      background-color: rgba(124, 197, 118,.1);
      padding: 20px;
      margin-top: 10px;
      text-align: left;">
        <div style="text-align: center; margin-bottom: 6px">
          <img src="//cdn.codingame.com/smash-the-code/statement/league_wood_04.png" />
        </div>
        <p style="text-align: center; font-weight: 700; margin-bottom: 6px;">
            Qu'est-ce qui vous attend dans les ligues supérieures ?
        </p>
        <p>
        Voici les règles supplémentaires à débloquer dans les ligues supérieures :
        <ul style="padding-bottom: 0;" class="statement-next-rules">
        <!-- END -->
          <!-- BEGIN level1 -->
          <li>En Bois 2, certaines créatures ont des capacité spéciales.</li>
        <!-- END -->
        <!-- BEGIN level1 level2 -->
          <li>En Bois 1, les joueurs peuvent jouer un nouveau type de carte : les <const>objets</const>.</li>
        <!-- END -->
        <!-- BEGIN level1 level2 level3 -->
        <li>En Bronze, plus de capacités spéciales !</li>
        <!-- END -->
        <!-- BEGIN level1 level2 level3 -->
        </ul>
      </p>
    </div>
    <!-- END -->
    <!-- END LEAGUE ALERT -->
  </div>
