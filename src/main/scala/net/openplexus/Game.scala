package net.openplexus

import com.badlogic.gdx.{Gdx, ApplicationListener}
import com.badlogic.gdx.graphics.{GL10, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * The game loop
 */
class Game(width: Int, height: Int) extends ApplicationListener {

  val camera = new OrthographicCamera()
  var batch: SpriteBatch = null
  var map: Map = null


  def create() {
    camera.setToOrtho(false, width, height)
    batch = new SpriteBatch()
    map = Map(batch)
  }

  def resize(width: Int, height: Int) {}

  def render() {
    Gdx.gl.glClearColor(0.0f, 0.0f, 0.6f, 1.0f)
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT)
    camera.update()

    batch.setProjectionMatrix(camera.combined)
    batch.begin()
    map.render(0, 0, width, height)
    batch.end()
  }

  def pause() {}

  def resume() {}

  def dispose() {
    batch.dispose()
    Assets.dispose()
  }
}

case class Map(batch: SpriteBatch) {

  def render(x: Int, y: Int, width: Int, height: Int) {
    for (xx <- x.to(width, 64)) {
      for (yy <- y.to(height, 64)) {
        batch.draw(Assets.getImage("grass2"), xx, yy)
      }
    }
  }

}
