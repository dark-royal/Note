package africa.semicolon.note.services;

import africa.semicolon.note.data.models.User;
import africa.semicolon.note.data.models.VerificationToken;
import africa.semicolon.note.data.repositories.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Calendar;
@Service
public class VerificationServiceImplementation{

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationServiceImplementation(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Transactional
    public VerificationToken findByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }

    @Transactional
    public void save(User user, String token){
        VerificationToken verificationToken = new VerificationToken(token,user);
        verificationToken.setExpiryDate(calculateExpiryDate(24));
        verificationTokenRepository.save(verificationToken);
    }

    private Timestamp  calculateExpiryDate(int expiryTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,expiryTimeInMinutes);
        return new java.sql.Timestamp(calendar.getTime().getTime());
    }
}
