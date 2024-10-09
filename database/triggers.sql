DROP TRIGGER IF EXISTS after_insert_user_tariffs;
DELIMITER //
Create trigger after_insert_user_tariffs
    After insert on `tariffsdb`.`user_tariffs`
    For each row
Begin
    Declare messages_counts int ;
    Declare calls_time Time ;
    Declare internet  int;
    Declare contract_id int;
    Declare prepaid_id int;
    INSERT INTO trigger_logs (log_message)
    VALUES (CONCAT('Trigger executed for id: ', NEW.id));
    Select contract_tariffs_id, total_messages_counts, total_calls_time, total_internet into contract_id, messages_counts, calls_time, internet
    From user_tariffs
             Join contract_tariffs on contract_tariffs.id=user_tariffs.contract_tariffs_id
    Where user_tariffs.id=NEw.id;
    if contract_id>0 then
    Update user_tariffs as ut
    Set  ut.rest_messages_counts=messages_counts, ut.rest_calls_time=calls_time, ut.rest_internet=internet
    Where ut.id=New.id;
    Else
    Select total_internet into internet
    From user_tariffs
             Join prepaid_tariffs on prepaid_tariffs.id=user_tariffs.prepaid_tariffs_id
    Where user_tariffs.id=NEw.id;

    Update user_tariffs as ut
    Set ut.rest_internet=internet
    Where ut.id=New.id;
End if ;




End //
DELIMITER ;