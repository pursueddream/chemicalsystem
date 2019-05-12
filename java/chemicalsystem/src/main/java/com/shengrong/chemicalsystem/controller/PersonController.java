package com.shengrong.chemicalsystem.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shengrong.chemicalsystem.controller.request.PageRequest;
import com.shengrong.chemicalsystem.controller.response.PageResult;
import com.shengrong.chemicalsystem.dao.PersonDao;
import com.shengrong.chemicalsystem.model.dto.ExcelDTO;
import com.shengrong.chemicalsystem.model.entity.PersonEntity;
import com.shengrong.chemicalsystem.utils.ExcelUtils;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@Slf4j
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    /**
     * 查询列表
     * @param request request
     * @return PageResult
     */
    @RequestMapping(method = RequestMethod.GET, value = "person/list")
    public PageResult<PersonEntity> getAll(PageRequest request){
        //查询条件
        PersonEntity entity = new PersonEntity();

        //包装查询条件
        Wrapper<PersonEntity> wrapper = new EntityWrapper<>(entity);

        //查询data
        Page<PersonEntity> page = new Page<>(request.getPageNumber(), request.getPageSize());
        List<PersonEntity> list = personDao.selectPage(page, wrapper);

        //查询total
        int total = personDao.selectCount(wrapper);

        //返回包装
        PageResult<PersonEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotal(total);
        return result;

    }

    /**
     * 查询单个
     * @param id id
     * @return PersonEntity
     */
    @RequestMapping(method = RequestMethod.GET, value = "person/{id}/detail")
    public PersonEntity getOne (@PathVariable String id) {
        return personDao.selectById(id);
    }

    /**
     *添加
     * @param entity entity
     * @return String
     */
    @RequestMapping(method = RequestMethod.POST, value = "person")
    public Object add (@RequestBody PersonEntity entity) {
        personDao.insert(entity);
        return ResponseUtils.getDefResponse();
    }

    /**
     *修改
     * @param entity entity
     * @return String
     */
    @RequestMapping(method = RequestMethod.PUT, value = "person/{id}")
    public Object update (@RequestBody PersonEntity entity, @PathVariable String id) {
        personDao.updateById(entity);
        return ResponseUtils.getDefResponse();
    }

    /**id
     *删除
     * @param id id
     * @return String
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "person/{id}")
    public Object update (@PathVariable String id) {
        personDao.deleteById(id);
        return ResponseUtils.getDefResponse();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public void testExcel (HttpServletResponse response) throws Exception {
        ExcelDTO dto = new ExcelDTO();
        dto.setTagName("xxx1");
        dto.setTitles(Arrays.asList("ID", "姓名", "年龄"));

        List<List<String>> data = new ArrayList<>();
        data.add(Arrays.asList("1", "张三", "20"));
        data.add(Arrays.asList("2", "李四", "30"));

        dto.setRows(data);

        ExcelUtils.exportExcel(response, "test", dto);

    }

}
