package lior.nataf.emailserver.handler;

import lior.nataf.emailserver.model.Email;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailHandlerContainer {

    private final Map<String, EmailHandler> emailHandlerMap;

    public EmailHandlerContainer(GmailHandler gmailHandler, WallaHandler wallaHandler, YahooHandler yahooHandler) {
        emailHandlerMap = new HashMap<>(){{
            put(gmailHandler.getPostfix(), gmailHandler);
            put(wallaHandler.getPostfix(), wallaHandler);
            put(yahooHandler.getPostfix(), yahooHandler);
        }};
    }

    public EmailHandler getHandlerByProviderPostfix(String postfix) {
        return emailHandlerMap.getOrDefault(postfix, null);
    }

    public boolean contains(String postfix) {
        return emailHandlerMap.containsKey(postfix);
    }

    public String extractVendorPostfixFromAddress(Email emailAddress) {
        //MUST BE VALID EMAIL ADDRESS ELSE RETURNS EMPTY STRING
        return "@" + emailAddress.getFrom().split("@")[1];
    }
}
