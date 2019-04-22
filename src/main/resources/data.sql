 insert into role(id, role)  values(1, 'ROLE_ADMIN');
 insert into role(id, role)  values(2, 'ROLE_USER');

  insert into users (id, password, email, username, name, last_name, active)
  values (1, '$2a$10$EH1RWzFggQlW9GYbobtZtO1ae4ZT//m4xIFgFDhe1miFTcS7z1hQW', 'admin@localhost.com', 'admin', 'Admin', 'Test', 1);
  insert into users (id, password, email, username, name, last_name, active)
  values (2, '$2a$10$EH1RWzFggQlW9GYbobtZtO1ae4ZT//m4xIFgFDhe1miFTcS7z1hQW', 'donald@localhost.com', 'donald', 'Donald', 'Merizaj', 1);

  insert into user_role (user_id, role_id)
  values (1, 1);
  insert into user_role (user_id, role_id)
  values (1, 2);
  insert into user_role (user_id, role_id)
  values (2, 2);

 insert into post_category (id, name ) values (1, 'Latest');
 insert into post_category (id, name ) values (2, 'Economy');
 insert into post_category (id, name ) values (3, 'Sport');
 insert into post_category (id, name ) values (4, 'Technology');
 insert into post_category (id, name ) values (5, 'Trending');

 insert into post (id, title, content, image, user_id) values (1, 'Tiktok: India bans video sharing app',
 'The Indian government has ordered Google and Apple to take down the Chinese-owned Tiktok video app after a court expressed concerns over the spread of pornographic material. Tiktok has already been banned in neighbouring Bangladesh
   and hit with a large fine in the United States for illegally collecting information from children. The app, which claims
     to have 500 million users worldwide including more than 120 million in India, has been fighting the effort to shut it
     down after a high court in Chennai called for the ban on 3 April.', '', 1);

 insert into post (id, title, content, image, user_id) values (2, 'Deutsche Bank faces action over $20bn Russian money-laundering scheme',
 'Germany’s troubled Deutsche Bank faces fines, legal action and the possible prosecution of “senior management” because of its role in a $20bn
   Russian money-laundering scheme, a confidential internal report seen by the Guardian says. The bank admits there is a high risk that
    regulators in the US and UK will take “significant disciplinary action” against it. Deutsche concedes that the scandal has hurt its
    “global brand” – and is likely to cause “client attrition”, loss of investor confidence and a decline in its market value. Deutsche
      Bank was embroiled in a vast money-laundering operation, dubbed the Global Laundromat. Russian criminals with links to the Kremlin,
       the old KGB and its main successor, the FSB, used the scheme between 2010 and 2014 to move money into the western financial system.
        The cash involved could total $80bn, detectives believe.', '', 1);

 insert into post (id, title, content, image, user_id) values (3, 'Ajax, sensation of Europe'': Dutch and Italian press react to Turin triumph',
 'Ajax’s 2-1 second-leg Champions League quarter-final win at Juventus generated an understandably rapturous reaction back home in the Netherlands.
  “Incredible, incredible,” said De Telegraaf. “The achievement did not come easily but it was deserved one hundred percent.” The name of Dutch
   football’s finest master was quickly and readily invoked. This was “the reason why Johan Cruyff started the Velvet Revolution in October 2010,”
     continued De Telegraaf, referring to the late former player and manager’s grand plan of reinstalling old-style Ajax values. De Volksrant said:
      “Just like in Madrid, Ajax won at Real. It was unbelievable. Ajax knocked Cristiano Ronaldo, a bystander, out of the tournament, the king of the
       Champions League who had been brought to Turin to win the cup here too.”',
  '', 1);

   insert into post (id, title, content, image, user_id) values (4, 'Would life be happier without Google? I spent a week finding out',
 'Halfway through my week without Google, my wife mentions that she would like to go out to see a film that evening, and I agree to deal
   with the logistics. In what I initially think is an inspired move, I drop by the local cinema on the way home and scribble down all the
  film times in my notebook. Then my wife insists on going to a different cinema. “Can I do this by phone?” I ask her. “Is 118 still a thing?”
    Turns out it is, and an expensive one: £2.50 a call, plus 75p a minute, plus a 55p access charge from my mobile provider. But more than a million
     people a year still use the service, and it even offers a text facility that answers questions – although you’re essentially just asking someone
      to Google something for you and text you back, for £3.50 a go.','', 1);

insert into posts_to_categories (id_post, id_category) values (1, 1);
insert into posts_to_categories (id_post, id_category) values (1, 4);

insert into posts_to_categories (id_post, id_category) values (2, 1);
insert into posts_to_categories (id_post, id_category) values (2, 2);

insert into posts_to_categories (id_post, id_category) values (3, 1);
insert into posts_to_categories (id_post, id_category) values (3, 3);

insert into posts_to_categories (id_post, id_category) values (4, 4);