package com.haulmont.creditProccesor.model;

import com.haulmont.creditProccesor.model.converters.MoneyConverter;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creditOffers")
public class CreditOffer {
    private static final Logger logger = LoggerFactory.getLogger(CreditOffer.class);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client = null;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private Credit credit = null;

    @Column(name = "creditAmount")
    @Convert(converter = MoneyConverter.class)
    private Money creditAmount = null;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Payment> paymentList = null;

    public CreditOffer() {
        super();
    }

    public CreditOffer(OfferBuilder offerBuilder) {
        this.client = offerBuilder.client;
        this.credit = offerBuilder.credit;
        this.creditAmount = offerBuilder.creditAmount;
        paymentList = offerBuilder.paymentList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Credit getCredit() {
        return credit;
    }

    public Money getCreditAmount() {
        return creditAmount;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    @Override
    public String toString() {
        return "CreditOffer{" +
                "id=" + id +
                ", client=" + client +
                ", credit=" + credit +
                ", creditAmount=" + creditAmount +
                ", paymentList=" + paymentList +
                '}';
    }

    public static class OfferBuilder {
        private PayStrategy payStrategy;
        private Client client;
        private Credit credit;
        private Money creditAmount;
        private LocalDate beginDate;
        private List<Payment> paymentList;

        public OfferBuilder() {
            super();
        }

        public OfferBuilder beginDate(LocalDate date) {
            this.beginDate = date;
            return this;
        }

        public OfferBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public OfferBuilder credit(Credit credit) {
            this.credit = credit;
            return this;
        }

        public OfferBuilder creditAmount(Money amount) {
            this.creditAmount = amount;
            return this;
        }

        public OfferBuilder payStrategy(PayStrategy payStrategy) {
            this.payStrategy = payStrategy;
            return this;
        }

        public CreditOffer build() {
            CreditOffer creditOffer = null;
            if (validateBuilder()) {
                paymentList = payStrategy.calculate(credit, creditAmount, beginDate);
                creditOffer = new CreditOffer(this);
            } else {
                logger.error("There is no valid data for CreditOffer building");
            }
            return creditOffer;
        }

        private boolean validateBuilder() {
            return (payStrategy != null
                    && client != null
                    && credit != null
                    && creditAmount != null
                    && beginDate != null);
        }
    }
}
