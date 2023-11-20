package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.utils.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public int createCredential(Credential credential, Integer userId) {
        Credential encrypted = encryptCredential(credential);
        return credentialMapper.insertCredential(new Credential(null, credential.getUrl(), credential.getUsername(), encrypted.getKey(), encrypted.getPassword(), userId));
    }

    public int updateCredential(Credential credential, Integer userId) {
        encryptCredential(credential);
        credential.setUserId(userId);
        return credentialMapper.updateCredential(credential);
    }

    public int deleteCredential(Integer credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }

    public List<Credential> getCredentialsFor(Integer userId) {
        return credentialMapper.getCredentialsFor(userId);
    }

    private Credential encryptCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        return credential;
    }

}

