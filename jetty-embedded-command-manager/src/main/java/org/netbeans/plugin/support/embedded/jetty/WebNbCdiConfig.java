package org.netbeans.plugin.support.embedded.jetty;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;
import javax.annotation.Resource;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author V. Shyhkin
 */
public class WebNbCdiConfig extends AbstractConfiguration {

    /**
     *
     * @param context
     * @throws java.lang.Exception
     */
    @Override
    public void preConfigure(WebAppContext context) throws Exception {

        if (context.getTempDirectory() != null) {
            context.getTempDirectory().deleteOnExit();
        }

       // Map<String, ? extends FilterRegistration> srf = (Map<String, FilterRegistration>) context.getServletContext().getFilterRegistrations();

        //
        // Here we must use isCDIEnabled() without parameter. So each webapp is processed
        //
        if (NbDeployHandler.getInstance().isCDISupported()) {
            if (context.getInitParameter("WELD_CONTEXT_ID_KEY") == null) {
                if (!"/WEB_APP_FOR_CDI_WELD".equals(context.getContextPath())) {
                    UUID id = UUID.randomUUID();
                    context.setInitParameter("WELD_CONTEXT_ID_KEY", id.toString());
                }
            }
        }


    }

    /**
     * Process web-default.xml, web.xml, override-web.xml
     *
     * @param context
     * @throws java.lang.Exception
     */
    @Override
    public void configure(WebAppContext context) throws Exception {
    }

    /* ------------------------------------------------------------------------------- */
    protected Resource findWebXml(WebAppContext context) throws IOException, MalformedURLException {
        return null;
    }


    /* ------------------------------------------------------------------------------- */
    @Override
    public void deconfigure(WebAppContext context) throws Exception {
    }

    @Override
    public void postConfigure(WebAppContext context) throws Exception {
    }

}
