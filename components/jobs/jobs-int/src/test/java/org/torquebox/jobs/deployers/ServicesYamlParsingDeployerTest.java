package org.torquebox.jobs.deployers;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jboss.deployers.structure.spi.DeploymentUnit;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.torquebox.test.mc.vdf.AbstractDeployerTestCase;

public class ServicesYamlParsingDeployerTest extends AbstractDeployerTestCase {

    private ServicesYamlParsingDeployer deployer;

    @Before
    public void setUp() throws Throwable {
        this.deployer = new ServicesYamlParsingDeployer();
        addDeployer( this.deployer );
    }

    /** Ensure that an empty services.yml causes no problems. */
    @Test
    public void testEmptyServicesYml() throws Exception {
        URL servicesYml = getClass().getResource( "empty.yml" );

        String deploymentName = addDeployment( servicesYml, "services.yml" );
        processDeployments( true );

        DeploymentUnit unit = getDeploymentUnit( deploymentName );

        assertNotNull( unit );

    }

    /** Ensure that a valid services.yml attaches metadata. */
    @Ignore
    @Test
    public void testValidServicesYml() throws Exception {
        URL servicesYml = getClass().getResource( "valid-services.yml" );

        String deploymentName = addDeployment( servicesYml, "services.yml" );
        processDeployments( true );

        DeploymentUnit unit = getDeploymentUnit( deploymentName );

        assertNotNull( unit );
    }

    @Test
    public void testRequiresSingletonHandlesNullParams() throws Exception {
        assertFalse( this.deployer.requiresSingleton( null ) );
    }

    @Test
    public void testRequiresSingletonReturnsFalseWhenNoSingletonKey() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put( "key_other_than_singleton", "value" );
        assertFalse( this.deployer.requiresSingleton( params ) );
    }

    @Test
    public void testRequiresSingletonReturnsFalseWhenSingletonKeyIsFalse() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "singleton", false );
        assertFalse( this.deployer.requiresSingleton( params ) );
    }

    @Test
    public void testRequiresSingletonReturnsTrueWhenSingletonKeyIsTrue() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "singleton", true );
        assertTrue( this.deployer.requiresSingleton( params ) );
    }

}
