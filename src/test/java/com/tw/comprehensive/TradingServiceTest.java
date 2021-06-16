package com.tw.comprehensive;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TradingServiceTest {

    @Test
    void should_log_new_trade_when_create_trade_given_new_trade() {
        //given
        TradeRepository tradeRepository = new TradeRepository();
        AuditService auditService = new AuditService();
        AuditService spyAuditService = Mockito.spy(auditService);
        TradingService tradingService = new TradingService(tradeRepository, spyAuditService);
        Trade trade = new Trade("test", "test");

        //when
        tradingService.createTrade(trade);

        //then
        Mockito.verify(spyAuditService).logNewTrade(trade);
    }

}