using System;
using Microsoft.AspNetCore.Mvc;
using PorscheApi.Managers;

namespace PorscheApi.Controllers
{
	[Controller]
	[Route("porsche/")]
	public class PorscheController:ControllerBase
	{
		private PorscheManager manager;
		public PorscheController()
		{
			this.manager = new PorscheManager();
		}
		[HttpGet]
		[Route("GetPorsche")]
		public List<Porsche> getPorsche()
		{
			return this.manager.GetPorsche();
		}
        [HttpGet]
        [Route("GetPorscheByID")]
        public List<Porsche> getPorscheByID(int id)
		{
			return this.manager.GetPorschesByID(id);
		}
		[HttpGet]
		[Route("SearchPorsche")]
		public List<Porsche> SearchPorsche(string text)
		{
			return this.manager.SearchPorsche(text);
		}
		//
		//	Add IResult !
		//
    }
}

