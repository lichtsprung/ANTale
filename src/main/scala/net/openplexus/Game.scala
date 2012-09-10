package net.openplexus

import com.badlogic.gdx.{Gdx, ApplicationListener}
import com.badlogic.gdx.graphics.{Color, GL10, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import collection.mutable
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
 * The game loop
 */
case class Game(var width: Int, var height: Int, fontSize: Float) extends ApplicationListener {

  val camera = new OrthographicCamera()
  var batch: SpriteBatch = null
  var map: Map = null
  var renderer: ShapeRenderer = null


  def create() {
    camera.setToOrtho(false, width, height)
    batch = new SpriteBatch()
    renderer = new ShapeRenderer()
    map = Map(batch, renderer, fontSize, width, height)
  }

  def resize(width: Int, height: Int) {
    this.width = width
    this.height = height
  }

  def render() {
    Gdx.gl.glClearColor(0.05f, 0.05f, 0.05f, 1.0f)
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT)
    camera.update()

    batch.setProjectionMatrix(camera.combined)
    renderer.setProjectionMatrix(camera.combined)

    batch.begin()
    renderer.begin(ShapeType.FilledRectangle)
    map.render()
    renderer.end()
    batch.end()
  }

  def pause() {}

  def resume() {}

  def dispose() {
    batch.dispose()
    Assets.dispose()
  }
}

case class Map(batch: SpriteBatch, renderer: ShapeRenderer, fontSize: Float, width: Int, height: Int) {
  val fields = mutable.Buffer[Field]()
  var offsetX = 0
  var offsetY = 0

  init()

  def init() {
    for (x <- 0.to(width / fontSize.toInt)) {
      for (y <- 0.to(height / fontSize.toInt)) {
        fields += Field(x, y, this)
      }
    }
    fields(35).entities += Player("Bobo", fields(35))
  }

  def render() {
    fields.foreach(field => {
      field.draw()
    })
  }

}


case class Field(x: Float, y: Float, map: Map) {
  var baseColor: Color = Color.DARK_GRAY
  val entities = mutable.Buffer[Entity]()


  def draw() {
    map.renderer.setColor(baseColor)
    map.renderer.filledRect(x * map.fontSize, y * map.fontSize, map.fontSize, map.fontSize)
    entities.foreach(entity => entity.draw(x * map.fontSize, y * map.fontSize, map.batch))
  }
}


case class Player(override val name: String, override var position: Field) extends Entity(name, position) {
  def draw(x: Float, y: Float, batch: SpriteBatch) {
    batch.setColor(Color.WHITE)
    Assets.drawString("@", x, y, batch)
  }
}

abstract case class Entity(name: String, var position: Field) {
  def draw(x: Float, y: Float, batch: SpriteBatch)
}



