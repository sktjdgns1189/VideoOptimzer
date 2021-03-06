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
package com.att.aro.core.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.ApplicationContext;

import com.att.aro.core.SpringContextUtil;
import com.att.aro.core.analytics.AnalyticsEvents;
import com.att.aro.core.analytics.IAnalyticsManager;
import com.att.aro.core.analytics.IGoogleAnalytics;
import com.att.aro.core.settings.Settings;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("LI_LAZY_INIT_STATIC")
public class GoogleAnalyticsUtil {
	private static GoogleAnalyticsUtil GA_UTIL_INSTANCE = null;
	private static IGoogleAnalytics sendAnalytics;
	private static AnalyticsEvents ANALYTICS_EVENTS;
	private static Settings aroConfigSetting;

	ApplicationContext configContext = SpringContextUtil.getInstance().getContext();
	AtomicInteger numTraces = new AtomicInteger(0);

	private GoogleAnalyticsUtil() {
		sendAnalytics = getAvailableAnalyticsImplementor();
	}

	private IGoogleAnalytics getAvailableAnalyticsImplementor() {
		IAnalyticsManager colmg = configContext.getBean(IAnalyticsManager.class);
		return colmg.getAvailableAnalytics(configContext);
	}

	public static IGoogleAnalytics getGoogleAnalyticsInstance() {
		if (GA_UTIL_INSTANCE == null) {
			GA_UTIL_INSTANCE = new GoogleAnalyticsUtil();
		}
		return sendAnalytics;
	}

	public static synchronized AnalyticsEvents getAnalyticsEvents() {
		if (ANALYTICS_EVENTS == null) {
			if (GA_UTIL_INSTANCE == null) {
				GA_UTIL_INSTANCE = new GoogleAnalyticsUtil();
			}
			ANALYTICS_EVENTS = GA_UTIL_INSTANCE.configContext.getBean(AnalyticsEvents.class);
		}
		return ANALYTICS_EVENTS;
	}

	public static Settings getConfigSetting() {
		if (aroConfigSetting == null) {
			if (GA_UTIL_INSTANCE == null) {
				GA_UTIL_INSTANCE = new GoogleAnalyticsUtil();
			}
			aroConfigSetting = GA_UTIL_INSTANCE.configContext.getBean(Settings.class);
		}
		return aroConfigSetting;
	}
	
	public int getAndIncrementTraceCounter() {
		return numTraces.getAndIncrement();
	}

}
