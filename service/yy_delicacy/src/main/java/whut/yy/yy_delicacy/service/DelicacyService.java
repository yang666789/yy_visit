package whut.yy.yy_delicacy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whut.yy.yy_delicacy.model.Delicacy;
import whut.yy.yy_delicacy.repository.DelicacyRepository;

import java.util.List;

@Service
public class DelicacyService {

    @Autowired
    DelicacyRepository delicacyRepository;

    public void addDelicacy(Delicacy delicacy) {
        delicacyRepository.addDelicacy(delicacy);
    }

    public void updateDelicacy(Delicacy delicacy) {
        delicacyRepository.updateDelicacy(delicacy);
    }

    public void deleteDelicacy(Integer id) {
        delicacyRepository.deleteDelicacy(id);
    }

    public PageInfo<Delicacy> getPages(String name, Integer tId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Delicacy> delicacies = delicacyRepository.getAll(name, tId);
        return new PageInfo<>(delicacies);
    }

    public PageInfo<Delicacy> getPagesByUId(Integer uId, String name, Integer tId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Delicacy> delicacies = delicacyRepository.getByUserId(uId, name, tId);
        return new PageInfo<>(delicacies);
    }

    public List<Delicacy> getDelicacyByUId(Integer uId) {
        return delicacyRepository.getByUserId(uId, null, null);
    }
}
