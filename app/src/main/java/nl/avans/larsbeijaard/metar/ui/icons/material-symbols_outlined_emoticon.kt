package nl.avans.larsbeijaard.metar.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Emoticon: ImageVector
	get() {
		if (_Emoticon != null) {
			return _Emoticon!!
		}
		_Emoticon = ImageVector.Builder(
            name = "nl.avans.larsbeijaard.metar.ui.icons.getEmoticon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
			path(
    			fill = SolidColor(Color.Black),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(260f, 680f)
				quadToRelative(-26f, 0f, -43f, -17f)
				reflectiveQuadToRelative(-17f, -43f)
				quadToRelative(0f, -25f, 17f, -42.5f)
				reflectiveQuadToRelative(43f, -17.5f)
				quadToRelative(25f, 0f, 42.5f, 17.5f)
				reflectiveQuadTo(320f, 620f)
				quadToRelative(0f, 26f, -17.5f, 43f)
				reflectiveQuadTo(260f, 680f)
				moveToRelative(0f, -280f)
				quadToRelative(-26f, 0f, -43f, -17f)
				reflectiveQuadToRelative(-17f, -43f)
				quadToRelative(0f, -25f, 17f, -42.5f)
				reflectiveQuadToRelative(43f, -17.5f)
				quadToRelative(25f, 0f, 42.5f, 17.5f)
				reflectiveQuadTo(320f, 340f)
				quadToRelative(0f, 26f, -17.5f, 43f)
				reflectiveQuadTo(260f, 400f)
				moveToRelative(140f, 120f)
				verticalLineToRelative(-80f)
				horizontalLineToRelative(160f)
				verticalLineToRelative(80f)
				close()
				moveToRelative(288f, 200f)
				lineToRelative(-66f, -44f)
				quadToRelative(28f, -43f, 43f, -92.5f)
				reflectiveQuadTo(680f, 480f)
				quadToRelative(0f, -66f, -21.5f, -124f)
				reflectiveQuadTo(598f, 251f)
				lineToRelative(61f, -51f)
				quadToRelative(48f, 57f, 74.5f, 128.5f)
				reflectiveQuadTo(760f, 480f)
				quadToRelative(0f, 67f, -19f, 127.5f)
				reflectiveQuadTo(688f, 720f)
			}
		}.build()
		return _Emoticon!!
	}

private var _Emoticon: ImageVector? = null
