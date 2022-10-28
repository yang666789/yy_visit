package whut.yy.yy_delicacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whut.yy.yy_delicacy.model.Delicacy_User;
import whut.yy.yy_delicacy.repository.DelicacyUserRepository;

import java.util.List;

@Service
public class DelicacyUserService {

    @Autowired
    DelicacyUserRepository delicacyUserRepository;

    public List<Delicacy_User> getByUId(Integer uId) {
        return delicacyUserRepository.getByUId(uId);
    }

    public void add(Delicacy_User delicacy_user) {
        delicacyUserRepository.add(delicacy_user);
    }

    public void delete(Integer uId, Integer dId) {
        delicacyUserRepository.delete(uId, dId);
    }
}
