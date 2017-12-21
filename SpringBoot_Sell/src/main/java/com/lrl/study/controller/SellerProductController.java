package com.lrl.study.controller;

import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.domain.ProductCategory;
import com.lrl.study.domain.ProductInfo;
import com.lrl.study.exception.SellException;
import com.lrl.study.form.ProductForm;
import com.lrl.study.repository.ProductCategoryRepository;
import com.lrl.study.service.Imp.ProductCategoryServiceImp;
import com.lrl.study.service.ProductCategoryService;
import com.lrl.study.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: <br>
 * 〈〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/18 13:42
 */
@RestController
@RequestMapping("/seller/product")
@CrossOrigin(origins = {},methods = {RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.GET})
public class SellerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 买家端商品列表显示
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Map list(@RequestParam(value="page",defaultValue = "1")Integer page,
                    @RequestParam(value="size",defaultValue = "10")Integer size){


        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.finfAll(request);
        Map map=new HashMap();
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return map;

    }

    /**
     * 获取商品信息
     * @param productId
     * @return
     */
    @GetMapping("/index")
    public Map index(@RequestParam(value = "productId")String productId){

        //1.根据producr查找商品
        ProductInfo productInfo = productInfoService.findOne(productId);
        //2.查找所有类目信息，返回list集合
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        //3.return map
        Map map = new HashMap();
        map.put("productInfo", productInfo);
        map.put("categoryList",productCategoryList);
        return map;
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @PostMapping("/onsale")
    public Map onsale(@RequestParam(value = "productId")String productId){

        Map map =new HashMap();
        try{
            //1.调用Service中的OnSale方法
            productInfoService.onSale(productId);
        }catch (SellException e){
            //2.吧Service抛出的异常捕获抛出
            map.put("msg",e.getMessage());
        }
        //3.return map
        map.put("msg","上架成功！");
        return map;
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @PostMapping("/offsale")
    public Map offsale(@RequestParam(value = "productId")String productId){

        Map map =new HashMap();
        try{
            //1.调用Service中的OffSale方法
            productInfoService.offSale(productId);
        }catch (SellException e){
            //2.吧Service抛出的异常捕获抛出
            map.put("msg",e.getMessage());
        }
        //3.return map
        map.put("msg","下架成功！");
        return map;
    }

    @PostMapping("/save")
    public Map save(@Valid ProductForm productForm, BindingResult bindingResult){
        //1.判断表单验证是否报错--》bindingResult
        Map map = new HashMap();
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
        try {
            //2.根据productForm中的productId查找findOne到商品
            ProductInfo productInfo = productInfoService.findOne(productForm.getProductId());
            //3.BeanUtils.copyProperties进行值得copy 并save
            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.save(productInfo);
        }catch (SellException e){
            //捕获并抛出异常
            map.put("msg", e.getMessage());
            return map;
        }
        //return map（“操作成功”）
        map.put("msg","操作成功！");
        return map;
    }
}
