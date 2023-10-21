select count(*) from profile pr left join post p on pr.profile_id = p.profile_id where p.post_id IS NULL;

select p.post_id from post p
                          inner join comment c on p.post_id = c.post_id
where p.title similar to '\d%' and length(p.content) > 20
group by p.post_id
having count(c.comment_id) = 2
order by p.post_id;

select p.post_id, count(c.comment_id) from post p left join comment c on p.post_id = c.post_id
group by p.post_id
having count(c.comment_id) = 0 OR  count(c.comment_id) = 1
order by p.post_id ASC LIMIT 3