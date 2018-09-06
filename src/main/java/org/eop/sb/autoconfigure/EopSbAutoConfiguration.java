package org.eop.sb.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixinjie
 * @since 2018-09-06
 */
@Configuration
@ComponentScan(basePackages = {"org.eop.common.autoconfigure.*"})
public class EopSbAutoConfiguration {

}
