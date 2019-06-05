package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.controller.request.OrderRequest;
import com.shengrong.chemicalsystem.controller.request.UserInfoRequest;
import com.shengrong.chemicalsystem.controller.response.common.PageResultResponse;
import com.shengrong.chemicalsystem.model.entity.OrderEntity;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;
import com.shengrong.chemicalsystem.service.OrderService;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    public PageResultResponse<OrderEntity> getPage(OrderRequest request){

        OrderEntity entity = new OrderEntity();
        entity.setName(request.getName());

        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageNumber(request.getPageNumber());
        pageEntity.setPageSize(request.getPageSize());

        return orderService.queryPage(entity, pageEntity);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{id}")
    public OrderEntity queryById(@PathVariable("id") String id){
        return orderService.queryById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order")
    public Object add(@RequestBody OrderEntity entity){
        orderService.insert(entity);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/order/{id}")
    public Object modifyById(@PathVariable("id") String id, @RequestBody OrderEntity entity){
        entity.setId(id);
        orderService.updateById(entity);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/order/{id}")
    public Object deleteById(@PathVariable("id") String id){
        orderService.deleteById(id);
        return ResponseUtils.getDefResponse();
    }


}
