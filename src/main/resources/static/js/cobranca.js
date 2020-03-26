

$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',' , thousands: '.' ,  });
	
	$('.js-atualizar-status').on('click', function(event){
		event.preventDefault();
		
		var botaoReceber =  $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
	});
});