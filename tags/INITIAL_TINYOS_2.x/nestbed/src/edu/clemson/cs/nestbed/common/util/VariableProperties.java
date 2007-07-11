/* $Id$ */
/*
 * VariableProperties.java
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * School of Computing
 * Clemson University
 * Andrew R. Dalton and Jason O. Hallstrom
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the
 *
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301, USA.
 */
package edu.clemson.cs.nestbed.common.util;


import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Provides a simple mechanism for defining properties in terms of values
 * of other properties.  For example, consider the following properties:
 * <br/>
 * <code>
 *     first.name=Andy
 *     last.name=Dalton
 *     name=${first.name} ${last.name}
 * </code>
 * <p/>
 * Now consider the following code:
 * <br/>
 * <code>
 *     System.out.println(p.getProperty("name"));
 * </code>
 * <p/>
 * The could would print &quot;Andy Dalton&quot;
 * <p/>
 * <b>NOTE:</b> This is evaluated using a regular language;
 * therefore nesting of variable names is <b>NOT</b> supported.
 * Consider this example:
 * <p/>
 * <code>
 *     var=first.name
 *     first.name=Andy
 *     last.name=Dalton
 *     name=${${var}} ${last.name}
 * </code>
 *
 * @author Andy Dalton
 * @since  23 July 2006
 */
public class VariableProperties extends Properties {
    // Group 1    Group 2       Group 3
    // ------------------------------------
    // <Some Text><${Some Text}><Some Text>
    private String  lineExp     = "^(.*)(\\$\\{.*\\})(.*)$";
    private Pattern linePattern = Pattern.compile(lineExp);

    //   Group 1
    // --------------
    // ${<Some Text>}
    private String  varExp      = "^\\$\\{(.*)\\}$";
    private Pattern varPattern  = Pattern.compile(varExp);


    public VariableProperties() {
        super();
    }


    public VariableProperties(Properties properties) {
        super(properties);
    }


    @Override
    public Object setProperty(String key, String value) {
        return super.setProperty(key, expandProperty(value));
    }


    @Override
    public String getProperty(String key) {
        return expandProperty(super.getProperty(key));
    }


    @Override
    public String getProperty(String key, String defaultValue) {
        return expandProperty(super.getProperty(key, defaultValue));
    }


    private String expandProperty(String propertyString) {
        if (propertyString != null) {
            Matcher lineMatcher = linePattern.matcher(propertyString);

            while (lineMatcher.find()) {
                String  prefix     = lineMatcher.group(1);
                String  dollarExp  = lineMatcher.group(2);
                String  postfix    = lineMatcher.group(3);

                Matcher varMatcher = varPattern.matcher(dollarExp);

                if (varMatcher.find()) {
                    String variable = varMatcher.group(1);
                    String value    = getProperty(variable);
                    propertyString  = prefix + value + postfix;
                    lineMatcher     = linePattern.matcher(propertyString);
                }
            }
        }

        return propertyString;
    }
}
