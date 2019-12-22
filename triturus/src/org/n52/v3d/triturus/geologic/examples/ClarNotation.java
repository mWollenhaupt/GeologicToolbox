/**
 * Copyright (C) 2019 52 North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *  - Apache License, version 2.0
 *  - Apache Software License, version 1.0
 *  - GNU Lesser General Public License, version 3
 *  - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *  - Common Development and Distribution License (CDDL), version 1.0.
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public License 
 * version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * Contact: Benno Schmidt, 52 North Initiative for Geospatial Open Source 
 * Software GmbH, Martin-Luther-King-Weg 24, 48155 Muenster, Germany, 
 * b.schmidt@52north.org
 */
package org.n52.v3d.triturus.geologic.examples;

import org.n52.v3d.triturus.core.T3dException;
import org.n52.v3d.triturus.geologic.util.Orientation;
import org.n52.v3d.triturus.gisimplm.GmPoint;
import org.n52.v3d.triturus.gisimplm.GmTriangle;
import org.n52.v3d.triturus.vgis.VgGeomObject;
import org.n52.v3d.triturus.vgis.VgPoint;
import org.n52.v3d.triturus.vgis.VgTriangle;

/**
 * Geologic Toolbox example application: Demonstrates <<tt>Orientation</tt> 
 * object handling and Clar notation generation
 * 
 * @author Benno Schmidt
 */
public class ClarNotation
{
	public static void main(String args[]) {
		new ClarNotation().run(args);
	}
	
	public void run(String[] args) 
	{ 
		try {
			VgPoint 
				p1 = new GmPoint(420000., 5800000., 100.),
				p2 = new GmPoint(420100., 5800000., 110.),
				p3 = new GmPoint(419900., 5800100., 100.);
			VgTriangle tri = new GmTriangle(p1, p2, p3);
			tri.setSRS(VgGeomObject.SRSNone);

			Orientation orient = new Orientation(tri); 
			
			if (orient.hasZeroArea()) 
				System.out.println("Area is 0.");
			if (orient.isPlain()) 
				System.out.println("Triangle plain in xy-plane.");
			
			System.out.println(
					"dip: " + orient.dipInt() + " (" + orient.dip() + ")");
			System.out.println(
					"azimuth: " + orient.azimuthInt() + " (" + orient.azimuth() + ")");
			System.out.println(
					"Clar: " + orient.clarNotation());
			System.out.println(
					"Compass direction: " + orient.compassDirection());
        }
		catch (T3dException e) {
			e.printStackTrace();
		}
	}
}