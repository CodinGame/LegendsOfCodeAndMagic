import { api as entityModule } from '../../entity-module/GraphicEntityModule.js'
import {lerp, unlerp, fitAspectRatio} from '../../core/utils.js'

export const api = {
  showDamage: true,
  showCurve: false
}

export class FXModule {
  constructor (assets) {
    this.wards = []
    this.time = 0
    this.impacts = []
    this.mustShrinkNickname = true
    this.nicknameIds = []
  }

  static get name () {
    return 'fx'
  }

  updateScene (previousData, currentData, progress) {
    this.wards = []
    this.impacts = []
    entityModule.entities.forEach(entity => {
      if (entity.currentState.image === 'ward.png' &&
      entity.currentState.alpha > 0 &&
      entity.currentState.visible) {
        this.wards.push(entity)
      }
      if ((entity.currentState.image === 'impact.png' || entity.currentState.image === 'heal.png') && entity.currentState.alpha > 0 && entity.currentState.visible) {
        this.impacts.push(entity)
      }
    })

    if (this.mustShrinkNickname) {
      this.mustShrinkNickname = false
      this.nicknameIds.forEach(entityId => {
        let entity = entityModule.entities.get(entityId)
        if (!entity.currentState.text || entity.currentState.text === '') {
          this.mustShrinkNickname = true
        } else {
          if (entity.graphics.width > 450) {
            let aspectRatio = fitAspectRatio(entity.graphics.width, entity.graphics.height, 450, 60)
            entity.graphics.scale.set(aspectRatio)
          }
        }
      })
    }
  }

  handleFrameData (frameInfo, nothing) {
    return {...frameInfo}
  }

  reinitScene (container, canvasData) {
    this.mustShrinkNickname = true
  }

  animateScene (delta) {
    this.time += delta
    const w = 210
    const h = 260
    for (let ward of this.wards) {
      ward.graphics.anchor.set(0.5)
      ward.graphics.position.set(w / 2, h / 2)
      let scale = lerp(0.85, 1, unlerp(-1, 1, Math.cos(this.time / 600)))

      ward.graphics.scale.set(
        scale * w / ward.graphics.texture.width,
        scale * h / ward.graphics.texture.height
      )
    }

    for (let impact of this.impacts) {
      impact.container.parent.visible = api.showDamage
    }
  }

  handleGlobalData (players, nicknameIds) {
    this.nicknameIds = nicknameIds || []
  }
}
