package whut.yy.yy_delicacy.converter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    //写入数据库
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, money.getAmountMinorLong());
    }

    //从数据库读出
    @Override
    public Money getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseToMoney(resultSet.getLong(s));
    }

    //从数据库读出
    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseToMoney(resultSet.getLong(i));
    }

    //从数据库读出
    @Override
    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseToMoney(callableStatement.getLong(i));
    }

    private Money parseToMoney(long value) {
        return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
    }
}
