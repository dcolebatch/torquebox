package org.torquebox.messaging.deployers;

import org.jboss.deployers.spi.DeploymentException;
import org.jboss.deployers.spi.deployer.DeploymentStages;
import org.jboss.deployers.spi.deployer.helpers.AbstractDeployer;
import org.jboss.deployers.structure.spi.DeploymentUnit;
import org.torquebox.mc.AttachmentUtils;
import org.torquebox.messaging.metadata.MessageProcessorMetaData;
import org.torquebox.messaging.metadata.QueueMetaData;
import org.torquebox.rack.metadata.RackApplicationMetaData;

/**
 * <pre>
 * Stage: DESCRIBE
 *    In: RackApplicationMetaData
 *   Out: QueueMetaData, MessageProcessorMetaData
 * </pre>
 * 
 * Any object can have tasks!
 */
public class BackgroundableDeployer extends AbstractDeployer {

    public BackgroundableDeployer() {
        setStage( DeploymentStages.DESCRIBE );
        setInput( RackApplicationMetaData.class );
        addOutput( MessageProcessorMetaData.class );
        addOutput( QueueMetaData.class );
    }

    @Override
    public void deploy(DeploymentUnit unit) throws DeploymentException {
        RackApplicationMetaData rackMetaData = unit.getAttachment( RackApplicationMetaData.class );

        QueueMetaData queue = new QueueMetaData();
        String queueName = "/queues/torquebox/" + rackMetaData.getRackApplicationName() + "/backgroundable";
        queue.setName( queueName );
        AttachmentUtils.multipleAttach( unit, queue, queue.getName() );

        // TODO: allow for configurable concurrency
        MessageProcessorMetaData processorMetaData = new MessageProcessorMetaData();
        processorMetaData.setDestinationName( queue.getName() );
        processorMetaData.setRubyClassName( "TorqueBox::Messaging::BackgroundableProcessor" );
        processorMetaData.setRubyRequirePath( "torquebox/messaging/backgroundable_processor" );
        AttachmentUtils.multipleAttach( unit, processorMetaData, processorMetaData.getName() );
    }

}
