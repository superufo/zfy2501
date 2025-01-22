package com.huadea.ext.config;

import lombok.extern.slf4j.Slf4j;

import javax.sip.*;
import javax.sip.address.*;
import javax.sip.header.*;
import javax.sip.message.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class GB28181ClientConfig {

    private SipFactory sipFactory;
    private SipStack sipStack;
    private SipProvider sipProvider;
    private AddressFactory addressFactory;
    private HeaderFactory headerFactory;
    private MessageFactory messageFactory;
    private ClientTransaction regTrans;
    private ListeningPoint udpListeningPoint;

    public GB28181ClientConfig(String myHost, int myPort, String serverIp, int serverPort, String deviceId, String devicePassword) {
        try {
            sipFactory = SipFactory.getInstance();
            sipFactory.setPathName("gov.nist");

            Properties properties = new Properties();
            properties.setProperty("javax.sip.STACK_NAME", "GB28181ClientConfig");
            properties.setProperty("javax.sip.IP_ADDRESS", myHost);

            log.info("Properties:{}",properties);
            sipStack = sipFactory.createSipStack(properties);

            headerFactory = sipFactory.createHeaderFactory();
            addressFactory = sipFactory.createAddressFactory();
            messageFactory = sipFactory.createMessageFactory();

            udpListeningPoint = sipStack.createListeningPoint(myHost, myPort, "udp");
            sipProvider = sipStack.createSipProvider(udpListeningPoint);

            register(serverIp, serverPort, deviceId, devicePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(String serverIp, int serverPort, String deviceId, String devicePassword) throws ParseException, InvalidArgumentException, SipException {
        SipURI requestURI = addressFactory.createSipURI(null, serverIp);
        requestURI.setPort(serverPort);

        SipURI fromURI = addressFactory.createSipURI(deviceId, serverIp);
        Address fromAddress = addressFactory.createAddress(fromURI);
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, "tag");

        SipURI toURI = addressFactory.createSipURI(deviceId, serverIp);
        Address toAddress = addressFactory.createAddress(toURI);
        ToHeader toHeader = headerFactory.createToHeader(toAddress, null);

        SipURI sipURI = addressFactory.createSipURI(deviceId, serverIp);
        sipURI.setPort(serverPort);
        Address address = addressFactory.createAddress(sipURI);
        ContactHeader contactHeader = headerFactory.createContactHeader(address);

        Header extensionHeader = headerFactory.createHeader("Subject", "Registration");
        MaxForwardsHeader maxForwardsHeader = headerFactory.createMaxForwardsHeader(70);

        log.info("serverIp:{},serverPort:{},deviceId:{},devicePassword:{}",serverIp,serverPort,deviceId,devicePassword);
        //        To: "The Little Blister" <sip:LittleGuy@there.com>
        //        From: "The Master Blaster" <sip:BigGuy@here.com>;tag=12345 20
        //        Call-ID: a4a08ea5f3563c128b4c1bbf219ca9b3@127.0.0.1
        //        CSeq: 1 INVITE
        //        Via: SIP/2.0/UDP 127.0.0.1:5060; branch=z9hG4bK1d32hr4
        //        Max-Forwards:70
        Request request = messageFactory.createRequest(requestURI,
                Request.REGISTER,
                sipProvider.getNewCallId(),
                headerFactory.createCSeqHeader(1L, Request.REGISTER),
                fromHeader,
                toHeader,
                Arrays.asList(extensionHeader),
                maxForwardsHeader);

        request.addHeader(contactHeader);

        // Add the authentication header
        AuthorizationHeader authHeader = headerFactory.createAuthorizationHeader("Digest");
        authHeader.setUsername(deviceId);
        authHeader.setRealm("*");
        authHeader.setNonce("nonce");
        authHeader.setURI(sipURI);
        authHeader.setAlgorithm("MD5");
        authHeader.setResponse(generateDigest(deviceId, devicePassword, "REGISTER", sipURI.toString(), "nonce"));
        request.addHeader(authHeader);

        regTrans = sipProvider.getNewClientTransaction(request);
        regTrans.sendRequest();
    }

    private String generateDigest(String username, String password, String method, String uri, String nonce) {
        // Implement the digest generation logic according to the RFC 2617
        // This is a placeholder for the actual digest calculation
        return "calculated_digest";
    }

    public void shutDown() {
        try {
            sipStack.deleteSipProvider(sipProvider);
            sipStack.deleteListeningPoint(udpListeningPoint);
            sipStack.stop();
        } catch (ObjectInUseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GB28181ClientConfig client = new GB28181ClientConfig("192.168.100.49", 6548, "192.168.100.49", 8116, "41010500002000000001", "12345678");
        try {
            Thread.sleep(5000);
        } catch (Exception ignored) {

        }
    }
}