DELIMITER //

CREATE PROCEDURE update_user_tariffs(IN user_tariff_id INT)
BEGIN
    DECLARE messages_counts INT;
    DECLARE calls_time TIME;
    DECLARE internet INT;
    DECLARE contract_id INT;

Select contract_tariffs_id, total_messages_counts, total_calls_time, total_internet into contract_id, messages_counts, calls_time, internet
From user_tariffs
         Join contract_tariffs on contract_tariffs.id=user_tariffs.contract_tariffs_id
Where user_tariffs.id=user_tariff_id;
if contract_id>0 then
Update user_tariffs as ut
Set  ut.rest_messages_counts=messages_counts, ut.rest_calls_time=calls_time, ut.rest_internet=internet
Where ut.id=user_tariff_id;
Else
Select total_internet into internet
From user_tariffs
         Join prepaid_tariffs on prepaid_tariffs.id=user_tariffs.prepaid_tariffs_id
Where user_tariffs.id=user_tariff_id;

Update user_tariffs as ut
Set ut.rest_internet=internet
Where ut.id=user_tariff_id;
End if ;

DELIMITER ;
