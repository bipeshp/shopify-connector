
package org.mule.modules.shopify.generated.connectivity;

import javax.annotation.Generated;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectionKey;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.8.0", date = "2016-02-19T04:46:03-03:00", comments = "Build UNNAMED.2762.e3b1307")
public class ConnectionManagementConfigShopifyConnectorConnectionKey implements ConnectionManagementConnectionKey
{

    /**
     * 
     */
    private String apikey;
    /**
     * 
     */
    private String password;

    public ConnectionManagementConfigShopifyConnectorConnectionKey(String apikey, String password) {
        this.apikey = apikey;
        this.password = password;
    }

    /**
     * Sets password
     * 
     * @param value Value to set
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Retrieves password
     * 
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets apikey
     * 
     * @param value Value to set
     */
    public void setApikey(String value) {
        this.apikey = value;
    }

    /**
     * Retrieves apikey
     * 
     */
    public String getApikey() {
        return this.apikey;
    }

    @Override
    public int hashCode() {
        int result = ((this.apikey!= null)?this.apikey.hashCode(): 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConnectionManagementConfigShopifyConnectorConnectionKey)) {
            return false;
        }
        ConnectionManagementConfigShopifyConnectorConnectionKey that = ((ConnectionManagementConfigShopifyConnectorConnectionKey) o);
        if (((this.apikey!= null)?(!this.apikey.equals(that.apikey)):(that.apikey!= null))) {
            return false;
        }
        return true;
    }

}
