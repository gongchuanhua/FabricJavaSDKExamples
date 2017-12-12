package ijarvis.intelliq.Fabric;


import ijarvis.intelliq.LedgerRecord;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static Logger logger=Logger.getLogger(AppTest.class);
    private static String CONNFIG_Orderer="grpc://192.168.188.111:7050";
    private static String CONNFIG_Peer0Org1="grpc://192.168.188.112:7051";
    private static String CONNFIG_Peer1Org1="grpc://192.168.188.113:7051";
    private static String CONNFIG_Peer0Org2="grpc://192.168.188.114:7051";
    private static String CONNFIG_Peer1Org2="grpc://192.168.188.115:7051";
    private static String CHANNELID="epointchannel";
    private static LedgerRecord PERSONINFO=new LedgerRecord("liudong","刘东","2017-12-12","江苏省张家港市","10000","江苏省苏州市张家港市国泰新点");
    @Before
    public void Setup() throws CryptoException, InvalidArgumentException {
        logger.debug("Fabric Test Init........");
        FabricApp fabricApp=new FabricApp();
        FabricApp.init();
    }
    @Test
    public void TestEpointChainCodeInstert() throws Exception {
        logger.debug("测试Fabric 插入功能");
        Channel channel = FabricApp.client.newChannel(CHANNELID);
        channel.addPeer(FabricApp.client.newPeer("peer", CONNFIG_Peer0Org1));
        channel.addOrderer(FabricApp.client.newOrderer("orderer", CONNFIG_Orderer));
        channel.initialize();
        FabricApp.instertFabcar(channel,PERSONINFO);
    }


    @Test
    public void TestEpointChainCodeQuery() throws Exception {
        logger.debug("测试Fabric 查询功能");
        Channel channel = FabricApp.client.newChannel(CHANNELID);
        channel.addPeer(FabricApp.client.newPeer("peer", CONNFIG_Peer0Org1));
        channel.addOrderer(FabricApp.client.newOrderer("orderer", CONNFIG_Orderer));
        channel.initialize();
        FabricApp.queryFabcar(channel, PERSONINFO.getPerid());
    }


}