package com.huadea.ext.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	@TableField(value="creator_id")
	private Integer creatorId;
}
