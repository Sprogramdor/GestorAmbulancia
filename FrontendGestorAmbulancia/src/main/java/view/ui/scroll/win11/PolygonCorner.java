package view.ui.scroll.win11;

import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.List;

public class PolygonCorner {

    public GeneralPath getRoundedGeneralPathFromPoints(final List<Point2D> points, final float arcSize) {
        points.add(points.get(0));
        points.add(points.get(1));
        final GeneralPath path = new GeneralPath();
        final Point2D startPoint = calculatePoint(points.get(points.size() - 1), points.get(points.size() - 2), arcSize);
        path.moveTo(startPoint.getX(), startPoint.getY());
        for (int pointIndex = 1; pointIndex < points.size() - 1; pointIndex++) {
            final Point2D previousPoint  = points.get(pointIndex - 1);
            final Point2D currentPoint  = points.get(pointIndex);
            final Point2D nextPoint  = points.get(pointIndex + 1);
            Point2D mPoint = calculatePoint(previousPoint , currentPoint , arcSize);
            path.lineTo(mPoint.getX(), mPoint.getY());
            mPoint = calculatePoint(nextPoint , currentPoint , arcSize);
            path.curveTo(currentPoint .getX(), currentPoint .getY(), currentPoint .getX(), currentPoint .getY(), mPoint.getX(), mPoint.getY());
        }
        return path;
    }

    private Point2D calculatePoint(final Point2D startPoint, final Point2D endPoint, final float arcSize) {
        final double distance  = Math.sqrt(Math.pow(startPoint.getX() - endPoint.getX(), 2f) + Math.pow(startPoint.getY() - endPoint.getY(), 2f));
        final double percentage  = arcSize / distance ;
        final double deltaX = (startPoint.getX() - endPoint.getX()) * percentage ;
        final double deltaY = (startPoint.getY() - endPoint.getY()) * percentage ;
        final double newX  = endPoint.getX() + deltaX;
        final double newY  = endPoint.getY() + deltaY;
        return new Point.Double(newX , newY );
    }
}
