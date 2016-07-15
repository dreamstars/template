import com.alibaba.citrus.service.configuration.ProductionModeAware;

/**
 * Webx Framework提供了一个接口:ProductionModeAware。Spring context中的beans, 如果实现了这个接口,就可以感知当前系统的运行模式
 * 有开发模式和生产模式
 *
 * Created by wangzhongxing on 16/5/26.
 */
public class ModuleLoaderServiceImpl implements ProductionModeAware {

    private boolean productionMode;

    public void setProductionMode(boolean productionMode) {
        this.productionMode = productionMode;
    }

    
}
