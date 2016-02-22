
package org.mule.modules.shopify.generated.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.shopify.ShopifyConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>ShopifyConnectorProcessAdapter</code> is a wrapper around {@link ShopifyConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.8.0", date = "2016-02-19T04:46:03-03:00", comments = "Build UNNAMED.2762.e3b1307")
public class ShopifyConnectorProcessAdapter
    extends ShopifyConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<ShopifyConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, ShopifyConnectorCapabilitiesAdapter> getProcessTemplate() {
        final ShopifyConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,ShopifyConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, ShopifyConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, ShopifyConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
