using System;
namespace PorscheApi.Managers
{
	public class PorscheManager
	{
		private DbA9e881Inka29shevch2929Context context;
		private List<Porsche> porscheList;
        public PorscheManager()
		{
			this.context = new DbA9e881Inka29shevch2929Context();
			this.porscheList = new List<Porsche>();
			getAllPorsche();
		}
		private void getAllPorsche()
		{
			this.porscheList = this.context.Porsches.ToList();
		}
		public List<Porsche> GetPorsche()
		{
			return this.porscheList;
		}
		public List<Porsche> GetPorschesByID(int id)
		{
			return this.porscheList.Where(x => x.Id.Equals(id)).ToList();
		}
		public List<Porsche> SearchPorsche(string text_search)
		{
			return this.porscheList.Where(x => x.Model.ToLower().Contains(text_search.ToLower())).ToList();
		}
	}
}

