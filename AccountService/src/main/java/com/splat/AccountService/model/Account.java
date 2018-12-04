package com.splat.AccountService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.concurrent.atomic.AtomicLong;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private Integer id;

    @NotNull
    @PositiveOrZero
    public AtomicLong amount;

}
