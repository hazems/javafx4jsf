/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.googlecode.javafx4jsf.component.jfxobject;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.googlecode.javafx4jsf.util.ComponentUtils;

/**
 * @author Hazem Saleh
 * @date Feb. 12, 2010
 * The (JFXObjectRenderer) renders a JavaFX Object.
 */
public class JFXObjectRenderer extends Renderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
        	Map<String, Object> parameters = ComponentUtils.getParameterMap(component);
            ResponseWriter  	writer     = context.getResponseWriter();
            JFXObject       	jfxObject  = (JFXObject) component;
            String          	script     = "<script> \n" +
                                             "javafx(\n" +
                                                 "{\n" +
                                                      "id: \"" + jfxObject.getClientId(context) + "\",\n" +
                                                      "archive: \"" + jfxObject.getArchive() + "\",\n" +
                                                      "width: " + jfxObject.getWidth() + ",\n" +
                                                      "height: " + jfxObject.getHeight() +",\n" +
                                                      "code: \"" + jfxObject.getCode() + "\",\n" +
                                                      "name: \"" + jfxObject.getWidgetName() + "\"" +
                                                      renderUserParameters(parameters) +
                                                  "}\n" +
                                          ");\n" +
                                          "</script>";            
            
            writer.write(script);
        }
    }

	private String renderUserParameters(Map<String, Object> parameters) {
		String parametersValue = "";
		
		for(String key : parameters.keySet()) {
			parametersValue += ", \n" + key + ": \"" + (String) parameters.get(key) + "\""; 
		}
		
		return parametersValue;
	}
}
