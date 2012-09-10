package net.openplexus

import com.badlogic.gdx.{Gdx, ApplicationListener}
import com.badlogic.gdx.graphics.{Color, GL10, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import collection.mutable
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import java.awt.image.BufferedImage
import java.awt

/**
 * The game loop
 */
case class Game(var screenWidth: Int, var screenHeight: Int, implicit val fontSize: Float) extends ApplicationListener {

  val camera = new OrthographicCamera()
  var map: Map = null
  implicit var batch: SpriteBatch = null
  implicit var renderer: ShapeRenderer = null


  def create() {
    camera.setToOrtho(false, screenWidth, screenHeight)
    batch = new SpriteBatch()
    renderer = new ShapeRenderer()
    map = Map(screenWidth, screenHeight)
  }

  def resize(width: Int, height: Int) {
    this.screenWidth = width
    this.screenHeight = height
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


/**
 * The world map.
 * @param extent the extent of the world map in meters
 * @param batch the batch renderer
 * @param renderer the shape renderer
 * @param fontSize the fontsize
 * @param resolution the end resolution of the world map in fields / m
 */
case class Map(extent:Int, resolution: Int)(implicit batch: SpriteBatch, implicit val renderer: ShapeRenderer, implicit val fontSize: Float) {
  val fields = mutable.Buffer[Field]()
  var offsetX = 0
  var offsetY = 0



  init()

  def init() {
    val image = new BufferedImage(extent, extent, BufferedImage.TYPE_4BYTE_ABGR)
    val g = image.getGraphics
    for(x <- 0.to(image.getWidth)){
      for(y<-0.to(image.getHeight)){
        val gray = math.random.toFloat
        g.setColor(new awt.Color(Color.rgb888(gray, gray, gray)))
      }
    }


  }

  def render() {
    fields.foreach(field => {
      field.draw()
    })
  }

}


case class Field(x: Float, y: Float)(implicit val renderer: ShapeRenderer, implicit val fontSize: Float, implicit val batch: SpriteBatch) {
  var baseColor: Color = Color.DARK_GRAY
  val entities = mutable.Buffer[Entity]()


  def draw() {
    renderer.setColor(baseColor)
    renderer.filledRect(x * fontSize, y * fontSize, fontSize, fontSize)
    entities.foreach(entity => entity.draw(x * fontSize, y * fontSize, batch))
  }
}


case class Player(var name: String, var position: Field) extends Entity(name, position) {
  def draw(x: Float, y: Float, batch: SpriteBatch) {
    batch.setColor(Color.WHITE)
    Assets.drawString("@", x, y, batch)
  }
}

abstract class Entity(name: String, position: Field) {
  def draw(x: Float, y: Float, batch: SpriteBatch)
}



