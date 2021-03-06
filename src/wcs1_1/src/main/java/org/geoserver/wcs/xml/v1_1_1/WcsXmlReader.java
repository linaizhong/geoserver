/* (c) 2014 Open Source Geospatial Foundation - all rights reserved
 * (c) 2001 - 2013 OpenPlans
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wcs.xml.v1_1_1;

import java.io.Reader;
import java.util.Map;

import javax.xml.namespace.QName;

import org.geoserver.ows.XmlRequestReader;
import org.geotools.util.Version;
import org.geotools.xml.Parser;
import org.vfny.geoserver.wcs.WcsException;

/**
 * Xml reader for wfs 1.0.0 xml requests.
 * 
 * @author Justin Deoliveira, The Open Planning Project
 * @author Andrea Aime, The Open Planning project
 *
 * TODO: there is too much duplication with the 1.1.0 reader, factor it out.
 */
public class WcsXmlReader extends XmlRequestReader {
    /**
     * Xml Configuration
     */
    WCSConfiguration configuration;

    public WcsXmlReader(String element, String version, WCSConfiguration configuration) {
        super(new QName(WCS.NAMESPACE, element), new Version(version), "wcs");
        this.configuration = configuration;
    }

    public Object read(Object request, Reader reader, Map kvp) throws Exception {
        //create the parser instance
        Parser parser = new Parser(configuration);
        parser.setValidating(true);
        parser.setFailOnValidationError(true);
        parser.setStrict(true);
        
        // parse
        Object parsed;
        try {
            parsed = parser.parse(reader);
        } catch(Exception e) {
            throw new WcsException("Parsing failed, the xml request is most probably not compliant to the wcs schema", e);
        }
        
        return parsed;
    }
}
