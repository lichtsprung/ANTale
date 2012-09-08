package net.openplexus

import com.badlogic.gdx.{Gdx, ApplicationListener}
import com.badlogic.gdx.graphics.Texture

/**
 * The game loop
 */
class Game extends ApplicationListener{
  var graphics: Texture = null

  def create() {
    graphics = new Texture(Gdx.files.internal("graphics.png"))
    println(graphics)
  }

  def resize(width: Int, height: Int) {}

  def render() {}

  def pause() {}

  def resume() {}

  def dispose() {}
}
