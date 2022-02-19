-- Creat a stored procedure, which will be used in the CallableStatement Demo
-- this procedure will increase the quantity of books that are less than [stock_limit] by [amount]
create procedure increase_stock(stock_limit int, amount int)
language plpgsql
as $$
begin
    update books
    set qty = qty + amount
    where qty < stock_limit;

    commit;
end;$$;
