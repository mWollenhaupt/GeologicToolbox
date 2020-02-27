package org.n52.v3d.triturus.geologic.exporters.util;

import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.n52.v3d.triturus.geologic.util.Orientation;
import org.n52.v3d.triturus.gisimplm.GmPoint;
import org.n52.v3d.triturus.gisimplm.GmTriangle;
import org.n52.v3d.triturus.vgis.VgGeomObject;
import org.n52.v3d.triturus.vgis.VgPoint;
import org.n52.v3d.triturus.vgis.VgTriangle;

/**
 * ShapeFileAttribute implementation for ClarNotation attributes. First the
 * shape file coulumns were initialized. After init the calculated attribute
 * values can be added. Note that column initialization and value assignment
 * must be in the same order
 *
 * @author MoritzWollenhaupt
 */
public class ClarNotationShapeFileAttribute extends ShapeFileAttribute {

    // calculable values of ClarNotation calculation
    private boolean dip, dipDir, strike, compassDirection;

    public ClarNotationShapeFileAttribute(boolean dip, boolean dipDir, boolean strike, boolean compassDirection) {
        this.dip = dip;
        this.dipDir = dip;
        this.strike = strike;
        this.compassDirection = compassDirection;
    }

    /**
     * Creates attribute's columns for the selected calculable values
     *
     * @param sftBuilder SimpleFeatureTypeBuilder that represents the shape
     * files basic structure
     */
    @Override
    public void init(SimpleFeatureTypeBuilder sftBuilder) {
        // eachs shape file attribute column contains an identifier and a data type
        if (dip) {
            sftBuilder.add("dip", Double.class);
        }
        if (dipDir) {
            sftBuilder.add("reserviert", Double.class);
        }
        if (strike) {
            sftBuilder.add("strike", Double.class);
        }
        if (compassDirection) {
            sftBuilder.add("compassDir", String.class);
        }
    }

    /**
     * Here the concrete values were calculated and added to the
     * SimpleFeatureBuilder
     *
     * @param sfBuilder SimpleFeatureBuilder that collects column values
     * @param points Pointarray, used for further calculations
     */
    @Override
    public void calcAttributes(SimpleFeatureBuilder sfBuilder, double[] points) {
        VgPoint p1 = new GmPoint(points[0], points[1], points[2]),
                p2 = new GmPoint(points[3], points[4], points[5]),
                p3 = new GmPoint(points[6], points[7], points[8]);
        VgTriangle tri = new GmTriangle(p1, p2, p3);
        tri.setSRS(VgGeomObject.SRSNone);

        Orientation orient = new Orientation(tri);

        if (dip) {
            sfBuilder.add(orient.dip());
        }
        if (dipDir) {
            sfBuilder.add(42.);
        }
        if (strike) {
            sfBuilder.add(orient.azimuth());
        }
        if (compassDirection) {
            sfBuilder.add(orient.compassDirection());
        }

    }

}
