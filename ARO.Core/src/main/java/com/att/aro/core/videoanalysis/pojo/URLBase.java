/*
 *  Copyright 2017 AT&T
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.att.aro.core.videoanalysis.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "URL")
@XmlType(propOrder = { "baseURL", "segment" })
public class URLBase {

	String baseURL;
	String segment;
	

	public String getSegment() {
		return segment;
	}

	@XmlElement(name = "Segment")
	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getBaseURL() {	
		return baseURL;
	}

	@XmlElement(name = "BaseURL")
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

}