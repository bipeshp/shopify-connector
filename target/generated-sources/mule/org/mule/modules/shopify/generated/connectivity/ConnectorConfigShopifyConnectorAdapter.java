
package org.mule.modules.shopify.generated.connectivity;

import javax.annotation.Generated;
import org.mule.api.ConnectionException;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectionAdapter;
import org.mule.devkit.internal.connection.management.TestableConnection;
import org.mule.modules.shopify.config.ConnectorConfig;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.8.0", date = "2016-02-19T04:46:03-03:00", comments = "Build UNNAMED.2762.e3b1307")
public class ConnectorConfigShopifyConnectorAdapter
    extends ConnectorConfig
    implements ConnectionManagementConnectionAdapter<ConnectorConfig, ConnectionManagementConfigShopifyConnectorConnectionKey> , TestableConnection<ConnectionManagementConfigShopifyConnectorConnectionKey>
{


    @Override
    public ConnectorConfig getStrategy() {
        return this;
    }

    @Override
    public void test(ConnectionManagementConfigShopifyConnectorConnectionKey connectionKey)
        throws ConnectionException
    {
        super.connect(connectionKey.getApikey(), connectionKey.getPassword());
    }

    @Override
    public void connect(ConnectionManagementConfigShopifyConnectorConnectionKey connectionKey)
        throws ConnectionException
    {
        super.connect(connectionKey.getApikey(), connectionKey.getPassword());
    }

    @Override
    public void disconnect() {
        super.disconnect();
    }

    @Override
    public String connectionId() {
        return super.connectionId();
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

}
