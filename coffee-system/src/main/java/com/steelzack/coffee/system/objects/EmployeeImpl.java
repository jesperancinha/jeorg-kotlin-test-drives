package com.steelzack.coffee.system.objects;

import com.steelzack.coffee.system.enums.Coffee;
import com.steelzack.coffee.system.enums.Payment;
import com.steelzack.coffee.system.objecs.Cup;
import com.steelzack.coffee.system.objecs.Employee;
import lombok.Getter;

@Getter
public class EmployeeImpl implements Employee {

    private Coffee coffee;

    private Cup cup;

    private Payment paymentType;
}
