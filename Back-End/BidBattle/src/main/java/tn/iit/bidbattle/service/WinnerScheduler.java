package tn.iit.bidbattle.service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.iit.bidbattle.dao.AuctionRepository;
import tn.iit.bidbattle.dto.AuctionDto;
import tn.iit.bidbattle.dto.BidDto;
import tn.iit.bidbattle.model.auction.AuctionStatus;

import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
@Transactional
public class WinnerScheduler {
    private final AuctionRepository auctionRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public WinnerScheduler(AuctionRepository auctionRepository, JavaMailSender javaMailSender) {
        this.auctionRepository = auctionRepository;
        this.javaMailSender = javaMailSender;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void sendMailForWinners(){
        auctionRepository.findAll()
                .stream()
                .filter(auctionDto -> AuctionStatus.ACTIVE.equals(auctionDto.getStatus()))
                .filter(auctionDto -> auctionDto.getEndDate().before(new Date()))
                .forEach(auctionDto -> {
                    auctionDto.getBids()
                            .stream()
                            .max(Comparator.comparingLong(BidDto::getPrice))
                            .ifPresent(bidDto -> sendMail(auctionDto, bidDto));
                    auctionDto.setStatus(AuctionStatus.ENDED);
                    auctionRepository.save(auctionDto);
                });
    }

    private void sendMail(AuctionDto auctionDto, BidDto bidDto) {
        try {
            var mimeMessage = javaMailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            messageHelper.setSubject("You have won the auction!");
            messageHelper.setFrom("bidbattle666@gmail.com");
            messageHelper.setTo(bidDto.getUser().getEmail());
            messageHelper.setText("Congratulations! You have won the auction for " + auctionDto.getProduct().getName() + " with a bid of " + bidDto.getPrice() + "dt.");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

}
