---
layout: default
title: select / poll / epoll

---
## select / poll / epoll

### select
	fd_set fd_in, fd_out;
	struct timeval tv;
	 
	// Reset the sets
	FD_ZERO( &fd_in );
	FD_ZERO( &fd_out );
	 
	// Monitor sock1 for input events
	FD_SET( sock1, &fd_in );
	 
	// Monitor sock2 for output events
	FD_SET( sock2, &fd_out );
	 
	// Find out which socket has the largest numeric value as select requires it
	int largest_sock = sock1 > sock2 ? sock1 : sock2;
	 
	// Wait up to 10 seconds
	tv.tv_sec = 10;
	tv.tv_usec = 0;
	 
	// Call the select
	int ret = select( largest_sock + 1, &fd_in, &fd_out, NULL, &tv );
	 
	// Check if select actually succeed
	if ( ret == -1 )
	    // report error and abort
	else if ( ret == 0 )
	    // timeout; no event detected
	else
	{
	    if ( FD_ISSET( sock1, &fd_in ) )
	        // input event on sock1
	 
	    if ( FD_ISSET( sock2, &fd_out ) )
	        // output event on sock2
	}

### poll

	// The structure for two events
	struct pollfd fds[2];
	 
	// Monitor sock1 for input
	fds[0].fd = sock1;
	fds[0].events = POLLIN;
	 
	// Monitor sock2 for output
	fds[1].fd = sock2;
	fds[1].events = POLLOUT;
	 
	// Wait 10 seconds
	int ret = poll( &fds, 2, 10000 );
	// Check if poll actually succeed
	if ( ret == -1 )
	    // report error and abort
	else if ( ret == 0 )
	    // timeout; no event detected
	else
	{
	    // If we detect the event, zero it out so we can reuse the structure
	    if ( pfd[0].revents & POLLIN )
	        pfd[0].revents = 0;
	        // input event on sock1
	
	    if ( pfd[1].revents & POLLOUT )
	        pfd[1].revents = 0;
	        // output event on sock2
	}

### epoll

	// Create the epoll descriptor. Only one is needed per app, and is used to monitor all sockets.
	// The function argument is ignored (it was not before, but now it is), so put your favorite number here
	int pollingfd = epoll_create( 0xCAFE ); 
	
	if ( pollingfd < 0 )
	 // report error
	
	// Initialize the epoll structure in case more members are added in future
	struct epoll_event ev = { 0 };
	
	// Associate the connection class instance with the event. You can associate anything
	// you want, epoll does not use this information. We store a connection class pointer, pConnection1
	ev.data.ptr = pConnection1;
	
	// Monitor for input, and do not automatically rearm the descriptor after the event
	ev.events = EPOLLIN | EPOLLONESHOT;
	// Add the descriptor into the monitoring list. We can do it even if another thread is 
	// waiting in epoll_wait - the descriptor will be properly added
	if ( epoll_ctl( epollfd, EPOLL_CTL_ADD, pConnection1->getSocket(), &ev ) != 0 )
	    // report error
	
	// Wait for up to 20 events (assuming we have added maybe 200 sockets before that it may happen)
	struct epoll_event pevents[ 20 ];
	
	// Wait for 10 seconds
	int ready = epoll_wait( pollingfd, pevents, 20, 10000 );
	// Check if epoll actually succeed
	if ( ret == -1 )
	    // report error and abort
	else if ( ret == 0 )
	    // timeout; no event detected
	else
	{
	    // Check if any events detected
	    for ( int i = 0; i < ret; i++ )
	    {
	        if ( pevents[i].events & EPOLLIN )
	        {
	            // Get back our connection pointer
	            Connection * c = (Connection*) pevents[i].data.ptr;
	            c->handleReadEvent();
	         }
	    }
	}
