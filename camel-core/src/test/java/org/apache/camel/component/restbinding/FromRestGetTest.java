/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.restbinding;

import org.apache.camel.CamelContext;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class FromRestGetTest extends ContextTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        // register our dummy rest capable component
        context.addComponent("dummy-rest", new DummyRestBindingCapableComponent());

        return context;
    }

/*    public void testFromRestModel() {
        assertEquals(1, context.getRoutes().size());

        RouteDefinition route = context.getRouteDefinition("foo");
        assertNotNull(route);

        FromRestDefinition from = (FromRestDefinition) route.getInputs().get(0);
        assertNotNull(from);
        assertEquals("get", from.getVerb());
        assertEquals("/hello", from.getPath());
        assertNull(from.getAccept());
    }

    public void testFromRest() throws Exception {
        getMockEndpoint("mock:foo").expectedMessageCount(1);

        template.sendBody("seda:get-hello", "Hello World");

        assertMockEndpointsSatisfied();
    }*/

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
//                rest()
//                    .get("/hello").to("mock:foo")
//                    .get("/bye").to("mock:bar");
            }
        };
    }
}