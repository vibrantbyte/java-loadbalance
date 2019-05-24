package github.vibrant;

/**
 * <b>Description:</b><br> : 轮询的端
 *
 * @author : suchao@renrenche.com
 * @version : 1.0
 * @Note <b>ProjectName:</b> java-loadbalance
 * <br><b>PackageName:</b> github.vibrant
 * <br><b>Date:</b> 2019/5/24 下午5:12
 */
public class RoundRobinPeer {

    /**
     * 编号
     */
    private Long serial;

    /**
     * 端名称
     */
    private String name;

    /**
     * 设置权重
     */
    private Integer weight;

    /**
     * 每轮完成之后的权重
     */
    private Integer currentWeight;

    /**
     * 活跃权重
     */
    private Integer effectiveWeight;

    /**
     * 是否忽略
     */
    private Boolean down;


    public RoundRobinPeer(Long serial, String name, Integer weight) {
        this.serial = serial;
        this.name = name;
        this.weight = weight;
    }


    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Integer getEffectiveWeight() {
        //todo
        return this.weight;
    }

    public Boolean getDown() {
        return down;
    }

    public void setDown(Boolean down) {
        this.down = down;
    }
}
