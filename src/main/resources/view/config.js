import { GraphicEntityModule } from './entity-module/GraphicEntityModule.js'
import { EndScreenModule } from './modules/endscreen/EndScreenModule.js'
import { TooltipModule } from './modules/tooltip/TooltipModule.js'
import { FXModule, api } from './modules/fx/FXModule.js'

export const modules = [
  GraphicEntityModule,
  EndScreenModule,
  TooltipModule,
  FXModule
]

export const gameName = 'LegendsOfCodeAndMagic'

export const options = [{
  title: 'SHOW LIFE CHANGE INFO',
  get: function () {
    return api.showDamage
  },
  set: function (value) {
    api.showDamage = value
  },
  values: {
    'ON': true,
    'OFF': false
  }
}]
